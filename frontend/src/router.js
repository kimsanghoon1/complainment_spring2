
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);


import ReceiptComplainmentManager from "./components/listers/ReceiptComplainmentCards"
import ReceiptComplainmentDetail from "./components/listers/ReceiptComplainmentDetail"

import PaymentFeeManager from "./components/listers/PaymentFeeCards"
import PaymentFeeDetail from "./components/listers/PaymentFeeDetail"

import UserUserManager from "./components/listers/UserUserCards"
import UserUserDetail from "./components/listers/UserUserDetail"

import ComplaintComplaintManager from "./components/listers/ComplaintComplaintCards"
import ComplaintComplaintDetail from "./components/listers/ComplaintComplaintDetail"


export default new Router({
    // mode: 'history',
    base: process.env.BASE_URL,
    routes: [
            {
                path: '/receipts/complainments',
                name: 'ReceiptComplainmentManager',
                component: ReceiptComplainmentManager
            },
            {
                path: '/receipts/complainments/:id',
                name: 'ReceiptComplainmentDetail',
                component: ReceiptComplainmentDetail
            },

            {
                path: '/payments/fees',
                name: 'PaymentFeeManager',
                component: PaymentFeeManager
            },
            {
                path: '/payments/fees/:id',
                name: 'PaymentFeeDetail',
                component: PaymentFeeDetail
            },

            {
                path: '/users/users',
                name: 'UserUserManager',
                component: UserUserManager
            },
            {
                path: '/users/users/:id',
                name: 'UserUserDetail',
                component: UserUserDetail
            },

            {
                path: '/complaints/complaints',
                name: 'ComplaintComplaintManager',
                component: ComplaintComplaintManager
            },
            {
                path: '/complaints/complaints/:id',
                name: 'ComplaintComplaintDetail',
                component: ComplaintComplaintDetail
            },



    ]
})
