require recipes-demos/common-demos.inc
inherit insane

EXTERNALSRC_BUILD := "${EXTERNALSRC}/demos/isp/isp_jpeg_4stream/build-v234ce-gnu-linux-d"
INSANE_SKIP_${PN} += "build-deps"
