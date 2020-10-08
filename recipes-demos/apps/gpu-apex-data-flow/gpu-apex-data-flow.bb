require recipes-demos/common-demos.inc

EXTERNALSRC_BUILD := "${EXTERNALSRC}/demos/apps/gpu_apex_data_flow/build-v234ce-gnu-linux-d"
DEPENDS += "gpu-viv-bin-s32v2xx"
RDEPENDS_${PN} += "gpu-viv-bin-s32v2xx"
