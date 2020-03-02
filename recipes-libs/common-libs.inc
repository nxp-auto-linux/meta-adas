require recipes-libs/common-elkms.inc
DEPENDS += "oal-driver"

do_install() {
	install -d "${INSTALL_DIR}/"
	install -m 0644 ${KO_MODULE_NAME}.ko "${INSTALL_DIR}/"
}

do_clean[postfuncs] += "do_clean_local"

do_clean_local () {
	make -C ${EXTERNALSRC_BUILD} ${EXTRA_OEMAKE} clean
}

