DESCRIPTION = "Vision SDK demo applications"
LICENSE = "Freescale-EULA"
LIC_FILES_CHKSUM = "file://${FSL_EULA_FILE};md5=88a13f4f35506a1e6c6049ffb53cffe7"


inherit externalsrc
inherit check_license

# We need to use a basic filepath here
SOURCES_PATH = "${@d.getVar('FSL_LOCAL_MIRROR', 1).replace('file://','')}"

EXTERNALSRC := "${SOURCES_PATH}/s32v234_sdk"

# Default APU compiler options: no APU compiler, using the precompiled tct
# graphs from the VSDK release
APU_OPT_DEFAULT = "APU_COMP=tct APU_PRECOMP=1"

# Use TCT APU toolchain to compile the APU graphs.
APU_OPT_TCT = "APU_COMP=tct APU_TOOLS=${APU_COMPILER_TCT}"

# Use NXP APU toolchain to compile the APU graphs.
APU_OPT_NXP = "APU_COMP=nxp APU_TOOLS=${APU_COMPILER_NXP}"

# If the NXP/TCT APU toolchain install path is defined in conf/local.conf
# the graphs are compiled with this toolchain
# Else, the default precompiled tct graphs are used
APU_OPT = "${@d.getVar('APU_OPT_NXP', True) if d.getVar('APU_COMPILER_NXP', True) \
	 else d.getVar('APU_OPT_TCT', True) if d.getVar('APU_COMPILER_TCT', True) \
	 else d.getVar('APU_OPT_DEFAULT', True)}"

EXTRA_OEMAKE = "${APU_OPT} allsub V=1 "

EXTRA_OEMAKE += "CROSS_COMPILE="${TARGET_PREFIX}""
EXTRA_OEMAKE += "CROSS_COMPILE_SYSROOT=--sysroot=\"${STAGING_DIR_TARGET}\""
EXTRA_OEMAKE += "CROSS_COMPILE_M0_ELF="${M0_TOOLCHAIN_DIR}/bin/arm-eabi-""

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
	make ${APU_OPT} cleansub
}

FILES_${PN} += "/${OUTPUT_DIR_NAME}"
FILES_${PN}-dbg += "/${OUTPUT_DIR_NAME}/.debug"
INSANE_SKIP_${PN} += "ldflags"
RDEPENDS_${PN} += "ocv"
DEPENDS_append = " dos2unix-native"
DEPENDS_append = " tcl-native"
DEPENDS_append = " m0-toolchain-native"

COMPATIBLE_MACHINE = "(s32v234.*)"
