var score = 2500;
var tiroX = 0;
var tiroY = 0;
var obj1 = false;
var obj2 = false;

function Bola(context, heroi, animacao) {
  this.context = context;
  this.animacao = animacao;
  //Tiro sair da ponta do personagem
  this.x = heroi.x + 38;
  this.y = heroi.y;

  this.a = heroi.angulo;
  this.b = heroi.forca;

  this.velocidadeX = 0;
  this.velocidadeY = 0;
  this.xy = 2;

  this.cor = 'gold';
  this.raio = 5;

  this.acertou = false;//acertar bau fica true
  this.desenhou = true;//se for falso sei que o tiro atingiu algo
  this.obj = false;//true se acertar o obstaculo
}
Bola.prototype = {
  atualizar: function () {
    // Percorrendo eixo X
    this.x += this.velocidadeX;

    // Percorrendo eixo Y
    this.xy += this.velocidadeX;
    var x = this.xy;
    this.y = ((x * x) * this.a) + (x * this.b) + 177;
    //var para tiro sair em intervalos
    tiroX = this.x;
    tiroY = this.y;

    //Ve se acertou o Bau
    if (825 > this.x && this.x > 750 && 196 > this.y && this.y > 180) {
      this.acertou = true;
      //incrementando score
      if (this.desenhou) {
        score += 500;
        this.desenhou = false;
      }
    }
    //redução do score
    if ((this.x > 839 && this.x < 843 || this.y > 249 && this.y < 253) && this.desenhou) {
      if (!obj1 && !obj2) {
        score -= 500;
      }
      else if (obj1) {
        score -= 1000
      }
      else {
        score -= 1500;
      }
      this.desenhou = false;
    }
    if (obj1 && !obj2) {
      if (this.x > barcoX && this.y > barcoY && this.x > (barcoX + 25) && this.x < (barcoX + 105) && this.y < (barcoY + 67) && this.desenhou || this.x > barcoX && this.y > barcoY && this.x < (barcoX + 180) && this.y > (barcoY + 67) && this.y < (barcoY + 180) && this.desenhou) {
        score -= 1000;
        this.desenhou = false;
        this.obj = true;
      }
    }
    else if (obj2) {
      if (this.x > aveX && this.y > aveY && this.x < (aveX + 80) && this.y < (aveY + 58) && this.desenhou) {
        score -= 1500;
        this.desenhou = false;
        this.obj = true;
      }
    }
    //GameOver
    if (score <= 0) {
      score = 0;
      $('#titulo').html("GAME OVER!");
      stop = true;
      $('#modal-over').modal({ show: true });
      $('#modal-over').on('click', '.btn-restart', function () {
        animacao.reiniciar();
      });
      $('#modal-over').on('click', '.btn-home', function () {
        animacao.reiniciar();
        $('body').removeClass('bug-scroll');
        $('#modal-game').modal('hide');
      });
    }
    //Win
    if (score == 10000) {
      $('#titulo').html("Parabéns! Todo dinheiro foi salvo!!!");
      stop = true;
      $('#modal-over').modal({ show: true });
      $('#modal-over').on('click', '.btn-restart', function () {
        animacao.reiniciar();
      });
      $('#modal-over').on('click', '.btn-home', function () {
        animacao.reiniciar();
        $('#modal-game').modal('hide');
      });
    }
  },
  desenhar: function () {
    var ctx = context;

    if (!this.acertou) {
      if (!this.obj) {
        // Configurar o contexto de acordo com a bola
        ctx.fillStyle = this.cor;

        // Desenhar
        ctx.beginPath();//armazena (linhas, arcos, etc) que juntos formam uma forma
        ctx.arc(this.x, this.y, this.raio, 0, 2 * Math.PI, false);
        ctx.fill();// pinta o caminho
      }
      if ((this.x < 900 && this.y < 380) && !this.desenhou) {
        ctx.font = "28px Open Sans";
        ctx.fillStyle = "gold";
        if (!obj1 && !obj2) {
          ctx.fillText("-500", 805, 72);
        }
        else if (obj1) {
          ctx.fillText("-1000", 805, 72);
        }
        else {
          ctx.fillText("-1500", 805, 72);
        }
      }
    }
    //else if cria pontos deixando x entre valores para pontos aparecer por um tempo
    else if (this.x < 900 && !this.obj) {
      this.pontos = new Image();
      this.pontos.src = "img/pontos.png";
      ctx.drawImage(this.pontos, 740, 130, 40, 40);
      ctx.font = "28px Open Sans";
      ctx.fillStyle = "gold";
      ctx.fillText("+500", 805, 72);
    }
  }
}