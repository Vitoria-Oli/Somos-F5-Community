<script></script>
<template>
  <div class="headerLogin">
    <a href="/" aria-current="page" class="headerLogo"></a>
  </div>
</template>

<style lang="scss">
@use "@/scss/colors" as c;
@use "@/scss/mixins" as m;

.headerLogin {
  left: 0%;
  top: 0%;
  right: 0%;
  bottom: 0%;
  display: flex;
  height: 5rem;
  -webkit-box-pack: center;
  justify-content: center;
  background-color: map-get(c.$colors, "light-orange");

  .headerLogo {
    background-image: url("https://uploads-ssl.webflow.com/62e2b7b9c42bdda27c83d493/6329c1bc9e1976ae0ada64b0_somos-f5.svg");
    background-size: contain;
    background-position: 0% 50%;
    background-repeat: no-repeat;
    width: 9rem;
    height: 4rem;
    margin: auto;
    margin-left: 1vw;
  }
}
</style>
