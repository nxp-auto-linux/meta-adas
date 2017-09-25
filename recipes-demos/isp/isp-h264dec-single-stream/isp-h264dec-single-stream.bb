require recipes-demos/common-demos.inc
inherit insane

EXTERNALSRC_BUILD := "${EXTERNALSRC}/demos/isp/isp_h264dec_single_stream/build-v234ce-gnu-linux-d"
INSANE_SKIP_${PN} += "build-deps"
