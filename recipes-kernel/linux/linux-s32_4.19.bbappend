FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "\
    file://0001-s32v234-dts-Add-VSDK-specific-configuration.patch \
    file://0002-fec-add-VSDK-specific-configuration-4.19.patch \
    file://0001-cse3-update-cse-to-run-IAK-commands.patch \
"
