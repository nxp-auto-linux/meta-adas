require recipes-demos/common-demos.inc
inherit insane

EXTERNALSRC_BUILD := "${EXTERNALSRC}/demos/apex/apex_math_lib/build-v234ce-gnu-linux-d"
DEPENDS += "ocv"
INSANE_SKIP_${PN} += "build-deps"
