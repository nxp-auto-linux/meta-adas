DESCRIPTION = "Vision SDK demo applications"
LICENSE = "Freescale-EULA"
LIC_FILES_CHKSUM = "file://${FSL_EULA_FILE};md5=d4f548f93b5fe0ee2bc86758c344412d"


inherit externalsrc

# We need to use a basic filepath here
SOURCES_PATH = "${@d.getVar('FSL_LOCAL_MIRROR', 1).replace('file://','')}"

EXTERNALSRC := "${SOURCES_PATH}/s32v234_sdk"

EXTRA_OEMAKE = "APU_COMP=tct APU_PRECOMP=1 allsub V=1 "
EXTRA_OEMAKE += "CROSS_COMPILE="${TARGET_PREFIX}""
EXTRA_OEMAKE += "CROSS_COMPILE_SYSROOT=--sysroot=\"${STAGING_DIR_TARGET}\""

OUTPUT_DIR_NAME = "vsdk/demos"
INSTALL_DIR = "${D}/${OUTPUT_DIR_NAME}"

fix_makefiles () {
  for file in ${FILE_CHANGE_SA_TO_LINUX}; do
    if grep "gnu-sa-d" $file; then
      sed -i -e 's/gnu-sa-d/gnu-linux-d/g' $file
    fi
  done
}

fix_arm_define () {
  file="${EXTERNALSRC}/build/nbuild/toolchains/aarch64-linux-gnu.mk"
  if grep "D__arm__" $file; then
    sed -i -e 's/-D__arm__/ /g' $file
  fi
}


do_configure_prepend() {
  fix_makefiles
  fix_arm_define
}

do_compile_prepend() {
  fix_makefiles
  fix_arm_define
}

do_install() {
	mkdir -p "${INSTALL_DIR}"
	cp *.elf "${INSTALL_DIR}/"
}


do_clean[postfuncs] += "do_clean_local"

do_clean_local () {
	cd ${EXTERNALSRC_BUILD}
	make APU_COMP=tct APU_PRECOMP=1 cleansub
}

FILES_${PN} += "/${OUTPUT_DIR_NAME}"
FILES_${PN}-dbg += "/${OUTPUT_DIR_NAME}/.debug"
INSANE_SKIP_${PN} += "ldflags"
RDEPENDS_${PN} += "ocv"

COMPATIBLE_MACHINE = "(s32v234evb)|(s32v234pcie)|(s32v234bbmini)"
