#!/bin/sh
### BEGIN INIT INFO
# Provides:          VSDK module insertion in specific order
# Required-Start:    $remote_fs $syslog
# Required-Stop:     $remote_fs $syslog
# Default-Start:     3 5
# Default-Stop:
# Short-Description: Loading VSDK modules
# Description:       Inserts VSDK kernel modules.
### END INIT INFO
# Copyright 2018 NXP


insmod VSDK_OUTPUT_DIR_NAME/apex.ko
insmod VSDK_OUTPUT_DIR_NAME/oal_cma.ko
insmod VSDK_OUTPUT_DIR_NAME/csi.ko
insmod VSDK_OUTPUT_DIR_NAME/cam.ko
insmod VSDK_OUTPUT_DIR_NAME/seq.ko
insmod VSDK_OUTPUT_DIR_NAME/fdma.ko
insmod VSDK_OUTPUT_DIR_NAME/h264enc.ko
insmod VSDK_OUTPUT_DIR_NAME/h264dcd.ko
insmod VSDK_OUTPUT_DIR_NAME/jpegdcd.ko
insmod VSDK_OUTPUT_DIR_NAME/viulite.ko
