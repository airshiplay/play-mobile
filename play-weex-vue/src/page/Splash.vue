<template>
    <div class="wrapper">
        <image :src="logo" class="logo" />
        <text class="greeting" @click="jump('/my')">The environment is ready!</text>
    </div>
</template>
<script>
    export default {
        name: 'App',
        data () {
            return {
                logo: 'https://gw.alicdn.com/tfs/TB1yopEdgoQMeJjy1XaXXcSsFXa-640-302.png'
            }
        },
        created: function () {
//            var router= this.$router
//            setTimeout(() => {
//                router.push('/my')
//            },2000)
        },methods:{
            jump(path){
                this.$router.push(path)
            }
        }
    }
</script>

<style scoped>
    .wrapper {
        justify-content: center;
        align-items: center;
    }
    .logo {
        width: 424px;
        height: 200px;
    }
    .greeting {
        text-align: center;
        margin-top: 70px;
        font-size: 50px;
        color: #41B883;
    }
    .message {
        margin: 30px;
        font-size: 32px;
        color: #727272;
    }
</style>