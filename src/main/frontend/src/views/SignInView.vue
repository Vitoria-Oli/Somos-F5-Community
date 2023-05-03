<script setup>
import HeaderLogin from '../components/HeaderLogin.vue';
import SignInComp from '../components/SignInComp.vue';
</script>

<template>
    <main class="main">
        <HeaderLogIn />
        <SignInComp/>
    </main>
</template>

<style scoped lang="scss">
.main {
    background-color: #FF4700;
    width: 100vw;
    height: 100vh;
}
</style>