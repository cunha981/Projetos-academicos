function Animacao(context) {
    this.context = context;
    this.sprites = [];
    this.ligado = false;
}
Animacao.prototype = {
    novoSprite: function (sprite) {
        this.sprites.push(sprite);
        sprite.animacao = this;
    },
    ligar: function () {
        this.ligado = true;
        this.proximoFrame();
    },
    desligar: function () {
        this.ligado = false;
    },
    reiniciar: function () {
        this.sprites = [];
        animacao.novoSprite(heroi);
        score = 2500;
        angulo = 3.5;
        forca = 1;
        arcX = 157;
        arcY = 176;
        barcoX = 400;
        barcoY = 53;
        aveX = 650;
        obj1 = false;
        obj2 = false;
        heroi.direcao = DIRECAO_DIREITA;
        heroi.x = 100;
        heroi.y = 175;
        heroi.forca = -0.099;
        heroi.angulo = 0.01;
        heroi.navioX = 23;
        heroi.baseX = 94;
        heroi.barra = 4.5;
        stop = false;
    },
    proximoFrame: function () {
        // Posso continuar?
        if (!this.ligado) return;

        // A cada ciclo, limpamos a tela
        this.limparTela();

        // Atualizamos o estado dos sprites
        for (var i in this.sprites)
            this.sprites[i].atualizar();

        // Desenhamos os sprites
        for (var i in this.sprites)
            this.sprites[i].desenhar();

        var animacao = this;
        // Chamamos o próximo ciclo
        /*método informa ao navegador que você deseja realizar uma animação e 
solicita que o navegador chame uma função especificada para atualizar uma animação*/
        requestAnimationFrame(function () {
            animacao.proximoFrame();
        });
    },
    limparTela: function () {
        var ctx = this.context;
        ctx.clearRect(0, 0, ctx.canvas.width, ctx.canvas.height);
    }
}
