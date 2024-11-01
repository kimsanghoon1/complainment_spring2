<template>

    <v-data-table
        :headers="headers"
        :items="getInfo"
        :items-per-page="5"
        class="elevation-1"
    ></v-data-table>

</template>

<script>
    const axios = require('axios').default;

    export default {
        name: 'GetInfoView',
        props: {
            value: Object,
            editMode: Boolean,
            isNew: Boolean
        },
        data: () => ({
            headers: [
                { text: "id", value: "id" },
            ],
            getInfo : [],
        }),
          async created() {
            var temp = await axios.get(axios.fixUrl('/getInfos'))

            temp.data._embedded.getInfos.map(obj => obj.id=obj._links.self.href.split("/")[obj._links.self.href.split("/").length - 1])

            this.getInfo = temp.data._embedded.getInfos;
        },
        methods: {
        }
    }
</script>

