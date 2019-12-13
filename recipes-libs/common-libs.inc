DESCRIPTION = "Vision SDK required kernel modules"
#HOMEPAGE = ""
LICENSE = "GPLv2"
#SECTION = ""
DEPENDS += "oal-driver"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

inherit externalsrc
inherit module

# We need the actual .ko name for appending to auto-insert variable
# This is usually the same as our recipe's filename
KO_MODULE_NAME = "${PN}"

# Workaround for the fact that our .ko recipes are not named
# using the conventional 'kernel-module-<ko_name>'
PROVIDES += "kernel-module-${@d.getVar('KO_MODULE_NAME', 1).replace('_','-')}${KERNEL_MODULE_PACKAGE_SUFFIX}"
RPROVIDES_${PN} += "kernel-module-${@d.getVar('KO_MODULE_NAME', 1).replace('_','-')}${KERNEL_MODULE_PACKAGE_SUFFIX}"

# We need to use a basic filepath here
SOURCES_PATH = "${@d.getVar('FSL_LOCAL_MIRROR', 1).replace('file://','')}"
EXTERNALSRC := "${SOURCES_PATH}/s32v234_sdk"

EXTRA_OEMAKE = "LINUX_S32V234_DIR=${KBUILD_OUTPUT} V=1"

# Make sure kernel modules are packaged unstripped
INHIBIT_PACKAGE_STRIP = "1"
INSTALL_DIR = "${D}${VSDK_OUTPUT_DIR_NAME}"

do_install() {
	install -d "${INSTALL_DIR}/"
	install -m 0644 ${KO_MODULE_NAME}.ko "${INSTALL_DIR}/"
}

oe_runmake_call() {
	bbnote "unset BUILDDIR; ${MAKE} ${EXTRA_OEMAKE} " "$@"
	(unset BUILDDIR; ${MAKE} ${EXTRA_OEMAKE} "$@")
}

do_clean[postfuncs] += "do_clean_local"

do_clean_local () {
	(unset BUILDDIR; make -C ${EXTERNALSRC_BUILD} ${EXTRA_OEMAKE} clean)
}

# Insert VSDK modules in a specific order as handled by vsdk-mod-order module
RDEPENDS_${PN} += "vsdk-mod-order"

FILES_${PN} += "${VSDK_OUTPUT_DIR_NAME} ${sysconfdir}/modules-load.d"
FILES_${PN}-dbg += "${VSDK_OUTPUT_DIR_NAME}/.debug"

COMPATIBLE_MACHINE = "(s32v234.*)"
