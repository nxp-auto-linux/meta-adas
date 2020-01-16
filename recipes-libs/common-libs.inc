require recipes-libs/common-elkms.inc
DEPENDS += "oal-driver"

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

