require recipes-demos/common-demos.inc
inherit insane

EXTERNALSRC_BUILD := "${EXTERNALSRC}/demos/apexcv_base/apexcv_simple/build-v234ce-gnu-linux-d"
INSANE_SKIP_${PN} += "build-deps"
