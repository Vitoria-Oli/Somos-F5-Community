<script setup>

</script>

<template>
<section class="bannerAdmin">
    <div class="infoAdmin">
        <div class="imgBanner">
         <img class="imgAdmin"  src="../assets/images/imagesSomosF5/logoF5.png" alt="">
        </div>
        <div class="nameAdmin">
            <h1>Somos F5</h1>
            <p>Administrador</p>
        </div>
    </div>
    <div class="designAdmin">
        <img class="bannerAdmin" src="../assets/images/imagesSomosF5/BannerAdmin.png" alt="">
    </div>
</section>
</template>

<style lang="scss">
@use "@/scss/colors" as c;
@use "@/scss/fonts";

.bannerAdmin{
 background-color: map-get(c.$colors, "light-green");
 width: 100%;
 display: flex;
 justify-content: space-between;
 align-items: center;
 border-radius: 5px;

    .infoAdmin{
    display: flex;
    margin-left: 4%;
    width: 30%;
    justify-content: space-evenly;
        .imgBanner{
            width: 120px;
            height: 120px;
            background-color: map-get(c.$colors, "white");
            border-radius: 100%;
            display: flex;
            align-items: center;
            justify-content: center;

            .imgAdmin{
                width: 80%;
            }
        }
            
            h1{
                
                width: 100%;
                font-size: 2.2em;
                color: map-get(c.$colors, "white");
                font-family: "openSans";
                font-weight: bolder;
                margin-left: 1%;
            }
            p{
                width: 100%;
                font-size: 1.5em;
                color: map-get(c.$colors, "grey");
                font-family: "openSans";
                font-weight: bold;
                margin-left: 1%;
            }
    }
    .designAdmin{
        margin-right: 2%;
        width: 30%;
    }

}

</style>