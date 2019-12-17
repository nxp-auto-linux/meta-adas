require recipes-demos/common-demos.inc

EXTERNALSRC_BUILD := "${EXTERNALSRC}/demos/avb/avb_isp_h264_4stream/build-v234ce-gnu-linux-d"

do_install_append() {
        mkdir "${INSTALL_DIR}/${PN}"
        cp vid_list.cfg "${INSTALL_DIR}/${PN}"
        cp ${EXTERNALSRC}/demos/avb/extern/firmware/h264prep/theA5App.bin "${INSTALL_DIR}/${PN}"
}

RDEPENDS_${PN} += "sm-drv"
