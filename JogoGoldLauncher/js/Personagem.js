// Códigos únicos para as direções
var DIRECAO_ESQUERDA = 1;
var DIRECAO_DIREITA = 2;
var angulo = 3.5;
var forca = 1;
var arcX = 157;
var arcY = 176;
var barcoX = 400;
var barcoY = 53;
var aveX = 650;
var aveY = 50;

function Heroi(context, teclado, animacao) {
    this.context = context;
    this.teclado = teclado;
    this.animacao = animacao;
    this.x = 100;
    this.y = 175;
    this.direcao = DIRECAO_DIREITA;
    this.navioX = 23;
    this.forca = -0.099;
    this.angulo = 0.01;
}
function Heroi(context) {
    this.direcao = DIRECAO_DIREITA;
    this.x = 100;
    this.y = 175;
    this.forca = -0.099;
    this.angulo = 0.01;
    this.navioX = 23;
    this.baseX = 94;
    this.barra = 4.5;
}
Heroi.prototype = {
    atualizar: function () {
        //navio indo e voltando
        if (this.navioX < 120 && this.direcao == DIRECAO_DIREITA) {
            this.x += 1;
            this.navioX += 1;
            this.baseX += 1;
            arcX += 1;
            barcoX += 0.4;
            barcoY -= 0.5;
            aveX += 0.8;
        } if (this.navioX >= 120) { this.direcao = DIRECAO_ESQUERDA; }

        if (this.navioX > 23 && this.direcao == DIRECAO_ESQUERDA) {
            this.x -= 1;
            this.navioX -= 1;
            this.baseX -= 1;
            arcX -= 1;
            barcoX -= 0.4;
            barcoY += 0.5;
            aveX -= 0.8;
        } if (this.navioX <= 23) { this.direcao = DIRECAO_DIREITA; }

        //aumentando angulo
        if (teclado.pressionada(SETA_CIMA) && this.apertadoC && this.forca > -2) {
            this.forca -= 0.1;
            this.apertadoC = false;
            angulo += 3.5;
            arcX -= 0.5;
            arcY -= 1;
        }
        //diminuindo angulo
        else if (teclado.pressionada(SETA_BAIXO) && this.apertadoB && this.forca < -0.1) {
            this.forca += 0.1;
            this.apertadoB = false;
            angulo -= 3.5;
            arcX += 0.5;
            arcY += 1;
        }
        //aumentando força
        else if (teclado.pressionada(SETA_DIREITA) && this.angulo > 0.0003) {
            this.angulo -= 0.0001;
            this.barra += 4;
            forca += 1
            //this.apertadoD = false;
        }
        //diminuindo força
        else if (teclado.pressionada(SETA_ESQUERDA) && this.angulo < 0.01) {
            this.angulo += 0.0001;
            this.barra -= 4;
            forca -= 1;
            //this.apertadoE = false;
        }
        !teclado.pressionada(SETA_BAIXO) ? this.apertadoB = true : null;
        !teclado.pressionada(SETA_CIMA) ? this.apertadoC = true : null;
        //!teclado.pressionada(SETA_ESQUERDA)? this.apertadoE = true: null;
        //!teclado.pressionada(SETA_DIREITA)? this.apertadoD = true: null;
    },
    desenhar: function () {
        this.logoGrupo = new Image();
        this.logoGrupo.src = "img/logoGrupo.png";
        context.drawImage(this.logoGrupo, 391, 316, 120, 40);

        //Barra de Força
        var x = 0.5;
        for (var i = 0.01; i >= 0.0002; i -= 0.0001) {
            x += 4;
            context.fillStyle = 'gray';
            context.fillRect(x, 312, 2, 30);
        }
        context.fillStyle = 'red'
        context.fillRect(this.barra, 310, 2, 34);
        context.font = "10px Arial";
        context.fillText("MIN", 3, 310);
        context.fillText("MAX", 375, 310);

        //Score na tela
        var gradient = context.createLinearGradient(0, 0, 0, 80);
        gradient.addColorStop("0", "#ffde00");
        gradient.addColorStop("1", "#ffae00");
        context.font = "28px Open Sans";
        context.fillStyle = gradient;
        context.fillText(score, 805, 42);
        
        //angulo e força na tela
        context.strokeStyle = "gold";
        context.strokeRect(800, 297, 75, 20);
        context.strokeRect(800, 322, 75, 20);
        context.font = "20px Arial";
        context.fillStyle = "blue";
        context.fillText("A: " + angulo + "°", 802, 314);
        context.fillText("F: " + forca + " %", 802, 339),

        this.pontos = new Image();
        this.pontos.src = "img/pontos.png";
        context.drawImage(this.pontos, 763, 6, 40, 40);

        this.pause = new Image();
        this.pause.src = "img/pause.png";
        context.drawImage(this.pause, 6, 6, 20, 20);

        this.bau = new Image();
        this.bau.src = "img/bau.png";
        context.drawImage(this.bau, 739, 137, 116, 102);

        this.navio = new Image();
        this.navio.src = "img/navio.png";
        context.drawImage(this.navio, this.navioX, 53, 193, 193);

        //colocando obstaculos
        if (score == 3500 && !obj2) obj1 = true;
        if (score == 5500) obj2 = true;
        if (obj1 && !obj2) {
            // if (obj1 && (score / 500) % 2 != 0) {
            this.barco = new Image();
            this.barco.src = "img/navioObstaculo.png";
            context.drawImage(this.barco, barcoX, barcoY, 180, 180);
        }
        else if (obj2) {
            obj1 = false;
            this.ave = new Image();
            this.ave.src = "img/passaro.png";
            context.drawImage(this.ave, aveX, aveY, 45, 45);
        }
        //simula angulo - barra no canhao
        context.beginPath();//armazena (linhas, arcos, etc) que juntos formam uma forma
        context.moveTo(this.x + 36, this.y + 4);
        context.lineTo(arcX, arcY);
        context.lineCap = "round";
        context.lineWidth = 2.5;
        context.strokeStyle = "gray";
        context.stroke();// pinta o caminho

        this.baseCanhao = new Image();
        this.baseCanhao.src = "img/canhaoBase.png";
        context.drawImage(this.baseCanhao, this.baseX, 178, 38, 26);

        this.canhao = new Image();
        this.canhao.src = "img/canhaoCima.png";
        context.drawImage(this.canhao, this.x, this.y, 38, 18);
    },
    atirar: function () {
        var tiro = new Bola(this.context, this, this.animacao);
        tiro.velocidadeX = 3;
        animacao.novoSprite(tiro);
    },
}