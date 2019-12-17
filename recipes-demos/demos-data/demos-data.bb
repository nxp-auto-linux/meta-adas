require recipes-demos/common-demos.inc
EXTERNALSRC_BUILD := "${EXTERNALSRC}/demos/"

do_configure() {
        :
}

do_compile() {
        :
}

do_install() {
        mkdir -p "${INSTALL_DIR}"
        cp -r data/ "${INSTALL_DIR}/"
}
