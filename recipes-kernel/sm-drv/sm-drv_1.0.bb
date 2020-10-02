# Copyright 2018 NXP

SUMMARY = "Secure Monitor driver"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

inherit module

SRC_URI = "git://source.codeaurora.org/external/autobsps32/extra/sm_drv;branch=master;protocol=https"
SRCREV = "8ce98accfed18a20a8bcd4d5f0b6d388ec1572a3"

S = "${WORKDIR}/git/driver"

# this module is not installed automatically
# a special manual procedure is needed when running AVB demos. 
# See <VSDK>/demos/avb/README.txt
EXTRA_OEMAKE_append = " KERNELDIR=${KBUILD_OUTPUT}"

do_install() {
	install -d "${D}/${VSDK_OUTPUT_DIR_NAME}"
	cp sm_drv.ko "${D}/${VSDK_OUTPUT_DIR_NAME}"
}


FILES_${PN} += "${VSDK_OUTPUT_DIR_NAME}/sm_drv.ko"

PROVIDES = "kernel-module-sm_drv${KERNEL_MODULE_PACKAGE_SUFFIX}"
RPROVIDES_${PN} = "kernel-module-sm_drv${KERNEL_MODULE_PACKAGE_SUFFIX}"

COMPATIBLE_MACHINE = "(s32v234.*)"
INHIBIT_PACKAGE_STRIP = "1"
