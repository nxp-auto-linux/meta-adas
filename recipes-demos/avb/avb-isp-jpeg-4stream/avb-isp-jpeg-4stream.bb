require recipes-demos/common-demos.inc
inherit insane

EXTERNALSRC_BUILD := "${EXTERNALSRC}/demos/avb/avb_isp_jpeg_4stream/build-v234ce-gnu-linux-d"
INSANE_SKIP_${PN} += "build-deps"

do_install_append() {
	mkdir "${INSTALL_DIR}/${PN}"
	cp vid_list.cfg "${INSTALL_DIR}/${PN}"
	cp ${EXTERNALSRC}/demos/avb/extern/firmware/jpeg/theA5App.bin "${INSTALL_DIR}/${PN}"
}

DEPENDS += "sm-drv"