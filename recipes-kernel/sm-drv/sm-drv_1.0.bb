# Copyright 2018 NXP

SUMMARY = "Secure Monitor driver"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

inherit module

SRC_URI = "git://source.codeaurora.org/external/autobsps32/extra/sm_drv;branch=master;protocol=https"
SRCREV = "ccccea7629752e884c3226de9f255e246eeb9de1"

S = "${WORKDIR}/git/driver"

# this module is not installed automatically
# a special manual procedure is needed when running AVB demos. 
# See <VSDK>/demos/avb/README.txt
DESTDIR = "/vsdk/demos"
EXTRA_OEMAKE_append = " KERNELDIR=${KBUILD_OUTPUT}"

do_install() {
	install -d "${D}/${DESTDIR}"
	cp sm_drv.ko "${D}/${DESTDIR}"
}


FILES_${PN} += "${DESTDIR}/sm_drv.ko"

PROVIDES = "kernel-module-sm_drv${KERNEL_MODULE_PACKAGE_SUFFIX}"
RPROVIDES_${PN} = "kernel-module-sm_drv${KERNEL_MODULE_PACKAGE_SUFFIX}"

COMPATIBLE_MACHINE = "(s32v234.*)"
INHIBIT_PACKAGE_STRIP = "1"
