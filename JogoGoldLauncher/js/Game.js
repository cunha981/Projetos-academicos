//Passa como atributo o elemento Canvas criado no index
var canvas = document.getElementById('game');
//Contexto em que vou trabalhar dentro do meu canvas
var context = canvas.getContext('2d');

var tiro = true;//var para tiro sair em intervalos(primeiro tiro)
var stop = false;//var que liga e desliga a função de atirar

var teclado = new Teclado(document);
var animacao = new Animacao(context);
// Um sprite pode ler o teclado para saber como se comportar
var heroi = new Heroi(context, teclado, animacao);
animacao.novoSprite(heroi);

$('#home-game').on('click', '.btn-game', function () {
    teclado.disparou(ESPACO, function () {
        if ((tiro == true || tiroX > 840 || tiroY > 250) && stop == false) {
            heroi.atirar();
            tiro = false;
            $('body').addClass('bug-scroll'); /* corrige problema onde o fundo rolava caso aparecesse o modal de game over ou de pause */
        }
    });
    $('#modal-game').modal('show');
});
$('.btn-game').click(animacao.ligar());
$('.modal-header').on('click', '.btn-close', function () {
    animacao.reiniciar();
    $('body').removeClass('bug-scroll');
    $('#modal-game').modal('hide');
});
$('.modal-header').on('click', '.btn-minus', function () {
    $('body').removeClass('bug-scroll');
    $('#modal-game').modal('hide');
});
/* scroll da navbar */
$(".o-jogo").click(function () {
    $('html, body').animate({
        scrollTop: $("#o-jogo").offset().top
    }, 2000);
});
$(".como-jogar").click(function () {
    $('html, body').animate({
        scrollTop: $("#como-jogar").offset().top
    }, 2000);
});
$(".integrantes").click(function () {
    $('html, body').animate({
        scrollTop: $("#integrantes-site").offset().top
    }, 2000);
});

/*
$('.navbar-header').on('click', '.navbar-toggle', function () {
    //alert("Teste");
    $( ".navbar-toggle" ).removeClass("barra-degrade-mobile");
});    
*/

$(".navbar-toggle").click(function () {
    /* barra some quando clica no ícone de menu uma vez, mas ao clicá-lo novamente, ela não reaparece (pendente) */
    $(".barra-degrade-mobile").css("visibility", "hidden");
});
function criaPause() {
    var posX = 6;
    var posY = 6;
    var larg = 20;
    var altu = 20;

    this.onload = function () {
        //conf do botao pause: ve onde o usuario clica;
        //se clicar na posição em que o botao está, pausar    
        canvas.onclick = function (evt) {
            var rectNav = canvas.getBoundingClientRect();
            var pos = {
                x: evt.clientX - rectNav.left,
                y: evt.clientY - rectNav.top
            };
            if (pos.x > posX && pos.x < (posX + larg) && pos.y > posY && pos.y < (posY + altu)) {
                stop = true;
                $('#modal-pause').modal({ show: true });
                $('#modal-pause').on('click', '.btn-restart', function () {
                    animacao.reiniciar();
                    //$('body').addClass('bug-scroll');
                    $('#modal-over').modal('hide');
                });
                $('#modal-pause').on('click', '.btn-home', function () {
                    animacao.reiniciar();
                    $('body').removeClass('bug-scroll');
                    $('#modal-over').modal('hide');
                    $('#modal-game').modal('hide');
                });
                $('#modal-pause').on('click', '.btn-play', function () {
                    stop = false;
                });
            }
        }
    };
}
criaPause();
