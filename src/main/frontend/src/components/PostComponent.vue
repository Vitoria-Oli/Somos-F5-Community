<script setup>
let random = Math.round(Math.random() * 2 + 1);
let image = "src/assets/images/separator" + random + ".png";
</script>

<template>
    <div class="post">
        <div class="info">
            <div class="nameContainer">
                <h1 class="nameUser">Saúl Aguinaga BackEnd God</h1>
                <h3 class="date">24-03-2023</h3>
            </div>
            <div class="postContainer">
                <h2 class="postTitle">Cómo hacer un buen CRUD en condiciones con Java</h2>
                <p class="textPubli">
                    Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum
                    has been the
                    industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and
                    scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap
                    into
                    electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the
                    release of
                    Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like
                    Aldus PageMaker including versions of Lorem Ipsum
                </p>
            </div>
        </div>
    </div>
    <div class="actionButtonsBox">
        <button class="editButton">
            <img src="../assets/images/svgPics/Editar_svg.png" alt="Edit">
        </button>
        <button class="deleteButton">
            <img src="../assets/images/svgPics/delete_svg.png" alt="Delete">
        </button>
    </div>
    <div class="separator" id="separator">
        <img class="stripe" :src="image" alt="Línea separadora de color morado.">
    </div>
</template>

<style scoped lang="scss">
@use "@/scss/colors" as c;

.post {
    background-color: map-get(c.$colors, "light-orange");
    display: flex;
    align-items: center;
    justify-content: center;
    margin-top: 2vw;

    .info {
        display: flex;
        justify-content: center;
        flex-direction: column;

        .nameContainer {
            display: flex;
            justify-content: space-between;
            text-align: center;
            align-items: center;

            .nameUser {
                font-size: 2vw;
                color: map-get(c.$colors, "black");
                font-family: 'Open Sans', sans-serif;
                font-weight: bold;
            }

            .date {
                color: map-get(c.$colors, "grey");
                font-family: 'Open Sans', sans-serif;
                font-size: 1.5vw;
            }
        }

        .postContainer {
            background-color: map-get(c.$colors, "white");
            border: 3px solid map-get(c.$colors, "grey");
            width: 60vw;

            .postTitle {
                font-size: 1.5vw;
                color: map-get(c.$colors, "black");
                font-family: 'Open Sans', sans-serif;
                font-weight: 600;
                margin-left: 2%;
                margin-top: 1%;
            }

            .textPubli {
                font-family: 'Open Sans', sans-serif;
                margin-left: 2%;
                margin-top: 1%;
            }
        }

    }
}

.actionButtonsBox{
    display: flex;
    justify-content: flex-end;
    width: 60vw;
    margin-top: 1vh;

    .editButton{
        height: 5vh;
        width: 5vw;
        margin-right: 2vw;

        :hover{
            background-color: rgb(194, 194, 194);
            border-radius: 5px;
        }
        :active{
            background-color: rgb(107, 107, 107);
            border-radius: 5px;
        }
    }

    .deleteButton{
        height: 5vh;
        width: 5vw;

        :hover{
            background-color: rgb(194, 194, 194);
            border-radius: 5px;
        }
        :active{
            background-color: rgb(107, 107, 107);
            border-radius: 5px;
        }
    }
}

.separator {
    display: flex;
    justify-content: center;
    align-items: end;
    margin: 2vw;

    .stripe {
        width: 70vw;
        height: 5vh;
    }
}
</style>


