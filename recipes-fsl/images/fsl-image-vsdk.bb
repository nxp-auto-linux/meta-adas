#
# Copyright 2017-2018 NXP
#

require recipes-fsl/images/fsl-image-auto.bb

# Add vsdk
IMAGE_INSTALL +=  "packagegroup-fsl-adas-vsdk"
