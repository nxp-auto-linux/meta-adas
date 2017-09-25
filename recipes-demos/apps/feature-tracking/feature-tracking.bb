require recipes-demos/common-demos.inc
inherit insane

EXTERNALSRC_BUILD := "${EXTERNALSRC}/demos/apps/feature_tracking/build-v234ce-gnu-linux-d"
INSANE_SKIP_${PN} += "build-deps"
