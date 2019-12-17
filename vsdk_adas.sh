#!/bin/bash

NAME_DEMOS_LIST=()
print_help() {
  echo "Usage:"
  echo " $0 -a=(nxp|tct) -bsp_root=BSP_ROOT -vsdk_root=VSDK_ROOT"
  echo " Generate meta adas dependencey bewteen VSDK and BSP:"
  echo " -vsdk_root - the root path for Vision SDK"
  echo " -bsp_root - the root path for BSP"
  echo " -help - help command"
}

has_dependency(){
  grep -E "$1" $2/BUILD.mk  2>&1 > /dev/null
}

add_demo() {
  local demo_dir=${BSP_ROOT}/sources/meta-adas/recipes-$(echo $1 | sed "s+_+-+g")
  local demo_name=$(echo $2 | sed "s+_+-+g")
  local demo_bb=${demo_dir}/${demo_name}.bb
  # get 3rd part libraries denendencies
  local  list_depends=()
  has_dependency "\-lopencv" ${VSDK_ROOT}/$1
  if [ $? -eq 0 ] ; then
    list_depends+=( ocv )
  fi
  has_dependency "\-lavcodec" ${VSDK_ROOT}/$1
  if [ $? -ne 0 ] ; then
    has_dependency "\-lavdevice" ${VSDK_ROOT}/$1
  fi
  if [ $? -ne 0 ] ; then
    has_dependency "\-lavfilter" ${VSDK_ROOT}/$1
  fi
  if [ $? -ne 0 ] ; then
    has_dependency "\-lavformat" ${VSDK_ROOT}/$1
  fi
  if [ $? -ne 0 ] ; then
    has_dependency "\-lavutil" ${VSDK_ROOT}/$1
  fi
  if [ $? -ne 0 ] ; then
    has_dependency "\-lswresample" ${VSDK_ROOT}/$1
  fi
  if [ $? -ne 0 ] ; then
    has_dependency "\-lswscale" ${VSDK_ROOT}/$1
  fi
  if [ $? -eq 0 ] ; then
    list_depends+=( ffmpeg )
  fi
  has_dependency "\-lboost" ${VSDK_ROOT}/$1
  if [ $? -eq 0 ] ; then
    list_depends+=( boost )
  fi

  mkdir -p ${demo_dir}
  cat << EOF > ${demo_bb}
require recipes-demos/common-demos.inc

EXTERNALSRC_BUILD := "\${EXTERNALSRC}/$1/build-v234ce-gnu-linux-d"
EOF
  for dep in "${list_depends[@]}" ; do
   cat << EOF >> ${demo_bb}
DEPENDS += "${dep}"
EOF
  done
}

update_avb_demos(){
  #avb/avb_isp_h264_1stream
  local filebb=${BSP_ROOT}/sources/meta-adas/recipes-demos/avb/$(echo avb_isp_h264_1stream/avb_isp_h264_1stream.bb | sed "s+_+-+g")
  cat << EOF >> ${filebb}

do_install_append() {
        mkdir "\${INSTALL_DIR}/\${PN}"
        cp vid_list.cfg "\${INSTALL_DIR}/\${PN}"
        cp \${EXTERNALSRC}/demos/avb/extern/firmware/h264prep/theA5App.bin "\${INSTALL_DIR}/\${PN}"
}

RDEPENDS_\${PN} += "sm-drv"
EOF

  #avb/avb_isp_h264_4stream
  filebb=${BSP_ROOT}/sources/meta-adas/recipes-demos/avb/$(echo avb_isp_h264_4stream/avb_isp_h264_4stream.bb | sed "s+_+-+g")
  cat << EOF >> ${filebb}

do_install_append() {
        mkdir "\${INSTALL_DIR}/\${PN}"
        cp vid_list.cfg "\${INSTALL_DIR}/\${PN}"
        cp \${EXTERNALSRC}/demos/avb/extern/firmware/h264prep/theA5App.bin "\${INSTALL_DIR}/\${PN}"
}

RDEPENDS_\${PN} += "sm-drv"
EOF

  #avb/avb_isp_jpeg_4stream
  filebb=${BSP_ROOT}/sources/meta-adas/recipes-demos/avb/$(echo avb_isp_jpeg_4stream/avb_isp_jpeg_4stream.bb | sed "s+_+-+g")
  cat << EOF >> ${filebb}

do_install_append() {
        mkdir "\${INSTALL_DIR}/\${PN}"
        cp vid_list.cfg "\${INSTALL_DIR}/\${PN}"
        cp \${EXTERNALSRC}/demos/avb/extern/firmware/jpeg/theA5App.bin "\${INSTALL_DIR}/\${PN}"
}
RDEPENDS_\${PN} += "sm-drv"
EOF
}

create_demos_data(){
  local demo_name=demos-data
  local demo_dir=${BSP_ROOT}/sources/meta-adas/recipes-demos/${demo_name}
  local filebb=${demo_dir}/${demo_name}.bb
  NAME_DEMOS_LIST+=( ${demo_name} )
  mkdir -p ${demo_dir}
  cat << EOF > ${filebb}
require recipes-demos/common-demos.inc
EXTERNALSRC_BUILD := "\${EXTERNALSRC}/demos/"

do_configure() {
        :
}

do_compile() {
        :
}

do_install() {
        mkdir -p "\${INSTALL_DIR}"
        cp -r data/ "\${INSTALL_DIR}/"
}
EOF
}

create_packagegroup_vsdk_demos(){
  local filebb=${BSP_ROOT}/sources/meta-adas/packagegroups/packagegroup-vsdk-demos.bb
  cat << EOF > ${filebb}
SUMMARY = "Required packages for Vision SDK"
LICENSE = "MIT"

inherit packagegroup

PROVIDES = "packagegroup-vsdk-demos"

RDEPENDS_packagegroup-vsdk-demos = "\\
EOF

  for demo in "${NAME_DEMOS_LIST[@]}" ; do
    cat << EOF >> ${filebb}
  $(echo ${demo} | sed "s+_+-+g") \\
EOF
  done
  cat << EOF >> ${filebb}
"
EOF
}

create_recipes_demos(){
  local demos_list=()
  demos_list+=( $(find ${VSDK_ROOT}/demos -type d -name "build-v234ce-gnu-linux-d" | grep -v -E '/libs/' | sort ) )
  for demo in "${demos_list[@]}" ; do
    local demo_vsdkdir=$(dirname ${demo})
    local demo_name=$(basename ${demo_vsdkdir})
    NAME_DEMOS_LIST+=( ${demo_name} )
    local demo_reldir=$(echo "${demo_vsdkdir}" | sed "s+${VSDK_ROOT}++g")
    if [[ ${demo_reldir} =~ ^/demos/.* ]] ; then
       demo_reldir=${demo_reldir:1}
    fi
    add_demo ${demo_reldir} ${demo_name}
  done

  update_avb_demos
  create_demos_data
  create_packagegroup_vsdk_demos
}

#parse optional arguments
until [ -z "$1" ] # Until all parameters used up . . .
do
  if [ $1 == "--" ] ; then
    break
  elif [[ $1 =~ ^-bsp_root=.* ]] ; then
    BSP_ROOT=${1:10}
  elif [[ $1 =~ ^-vsdk_root=.* ]] ; then
    VSDK_ROOT=${1:11}
  elif [ $1 == "-help" ] ; then
    print_help
    exit 0
  else
    echo "unsupported option: $1";
    print_help
    exit 1
  fi
  shift
done

if [ -z "${BSP_ROOT}" ] ; then
  echo "Please set the BSP ROOT name by using the -bsp_root option (e.g. -bsp_root=/home/adas-auto-yocto-bsp)!"
  exit 1
fi

if [ -z "${VSDK_ROOT}" ] ; then
  echo "Please set the VSDK ROOT name by using the -vsdk_root option (e.g. -vsdk_root=/home/VisionSDK_S32V2_RTM_1_5_0/s32v234_sdk )!"
  exit 1
fi

create_recipes_demos
