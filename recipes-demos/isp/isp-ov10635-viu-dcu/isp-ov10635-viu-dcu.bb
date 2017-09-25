require recipes-demos/common-demos.inc
inherit insane

EXTERNALSRC_BUILD := "${EXTERNALSRC}/demos/isp/isp_ov10635_viu_dcu/build-v234ce-gnu-linux-d"
INSANE_SKIP_${PN} += "build-deps"
