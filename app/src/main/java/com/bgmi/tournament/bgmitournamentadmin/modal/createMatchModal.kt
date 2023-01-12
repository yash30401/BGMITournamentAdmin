package com.bgmi.tournament.bgmitournamentadmin.modal

import androidx.annotation.Keep

@Keep
data class createMatchModal(var uploadDate:String?=null,var uploadTime:String?=null,var refId:String?=null,var matchCharge:String?=null,var slots:String?=null,
                            var matchTime:String?=null,var matchDate:String?=null,var imageUrl:String?=null,var matchDuration:String?=null,var matchCategory:String?=null,
                            var roomId:String?=null,var roomPass:String?=null,var prize:String?=null)

