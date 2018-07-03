# Copyright 2018 NXP

SUMMARY = "Insert VSDK in specific order"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI += " \
	file://vsdk_mod.sh \
"
do_install_append() {
	install -d ${D}/${sysconfdir}/init.d
	install -d ${D}/${sysconfdir}/rc5.d
	install -d ${D}/${sysconfdir}/rc3.d
	install -m 755 ${WORKDIR}/vsdk_mod.sh ${D}/${sysconfdir}/init.d/vsdk_mod.sh
	ln -sf ../init.d/vsdk_mod.sh      ${D}${sysconfdir}/rc5.d/S90vsdk_mod.sh
	ln -sf ../init.d/vsdk_mod.sh      ${D}${sysconfdir}/rc3.d/S90vsdk_mod.sh
}

FILES_${PN} += "${base_libdir}/*"
FILES_${PN} += "${sysconfdir}/init.d/*"
FILES_${PN} += "${sysconfdir}/rc5.d/*"
FILES_${PN} += "${sysconfdir}/rc3.d/*"
