package com.upsidedowncake.cell

import android.telephony.CellIdentityCdma
import android.telephony.CellIdentityGsm
import android.telephony.CellIdentityLte
import android.telephony.CellIdentityNr
import android.telephony.CellIdentityWcdma
import android.telephony.CellInfo

fun CellInfo.cellIdentityString(): String {

    return when (this.cellIdentity) {
        is CellIdentityGsm -> (this.cellIdentity as CellIdentityGsm).identityString()
        is CellIdentityCdma -> (this.cellIdentity as CellIdentityCdma).identityString()
        is CellIdentityWcdma -> (this.cellIdentity as CellIdentityWcdma).identityString()
        is CellIdentityLte -> (this.cellIdentity as CellIdentityLte).identityString()
        is CellIdentityNr -> (this.cellIdentity as CellIdentityNr).identityString()
        else -> this.cellIdentity.toString()
    }
}

private fun CellIdentityGsm.identityString(): String {
    return "MCC: ${this.mccString} MNC: ${this.mncString}\nLAC: ${this.lac} ID: ${this.cid}"
}

private fun CellIdentityCdma.identityString(): String {
    return "Basestation: ${this.basestationId} Network: ${this.networkId}"
}

private fun CellIdentityWcdma.identityString(): String {
    return "MCC: ${this.mccString} MNC: ${this.mncString}\nLAC: ${this.lac} ID: ${this.cid}"
}

private fun CellIdentityLte.identityString(): String {
    return "MCC: ${this.mccString} MNC: ${this.mncString}\nTAC: ${this.tac} ID: ${this.ci}"
}

private fun CellIdentityNr.identityString(): String {
    return "MCC: ${this.mccString} MNC: ${this.mncString}\nTAC: ${this.tac} ID: ${this.nci}"
}