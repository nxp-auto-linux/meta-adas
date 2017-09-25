require recipes-demos/common-demos.inc
inherit insane

EXTERNALSRC_BUILD := "${EXTERNALSRC}/demos/apps/apex_isp_face_detection_cv/build-v234ce-gnu-linux-d"
INSANE_SKIP_${PN} += "build-deps"
