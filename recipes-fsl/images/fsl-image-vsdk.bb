#
# Copyright 2017-2018 NXP
#

VSDK_IMAGE_BASE ??= "fsl-image-auto.bb"
VSDK_IMAGE_BASE_ubuntu ?= "fsl-image-ubuntu.bb"
require recipes-fsl/images/${VSDK_IMAGE_BASE}

# Add vsdk
IMAGE_INSTALL +=  "packagegroup-fsl-adas-vsdk"
IMAGE_INSTALL += "sm-drv"
