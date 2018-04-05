DESCRIPTION = "Vision SDK required kernel modules"
#HOMEPAGE = ""
LICENSE = "GPLv2"
#SECTION = ""
#DEPENDS = ""
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

# Make sure we have the kernel build dir available
do_configure[depends] += "virtual/kernel:do_shared_workdir"

EXTRA_OEMAKE = "LINUX_S32V234_DIR=${KBUILD_OUTPUT}"

# Make sure kernel modules are packaged unstripped
INHIBIT_PACKAGE_STRIP = "1"

OUTPUT_DIR_NAME = "/lib/modules/${KERNEL_VERSION}"
INSTALL_DIR = "${D}${OUTPUT_DIR_NAME}"

do_install() {
	install -d "${INSTALL_DIR}/"
	install -m 0644 ${KO_MODULE_NAME}.ko "${INSTALL_DIR}/"
}

do_clean() {
	make "LINUX_S32V234_DIR=${KBUILD_OUTPUT}" cleansub
	rm -rf ${WORKDIR}
}

# Auto insert module
KERNEL_MODULE_AUTOLOAD += "${KO_MODULE_NAME}"

FILES_${PN} += "${OUTPUT_DIR_NAME} ${sysconfdir}/modules-load.d"
FILES_${PN}-dbg += "${OUTPUT_DIR_NAME}/.debug"

COMPATIBLE_MACHINE = "(s32v234evb)|(s32v234pcie)|(s32v234bbmini)"
