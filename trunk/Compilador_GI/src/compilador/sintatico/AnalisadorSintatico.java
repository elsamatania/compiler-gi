/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package compilador.sintatico;

import compilador.gui.Janela;
import compilador.token.Token;
import compilador.token.TokenType;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Gabriel
 */
public class AnalisadorSintatico {

    //constantes correspondentes aos nomes dos não terminais que necessitam de conjunto primeiro na análise
    
    public static final String BLOCO_CONSTANTES = "bloco_constantes";
    public static final String BLOCO_VARIAVEIS = "bloco_variaveis";
    public static final String CLASSES = "classes";
    public static final String DECL_CONSTANTES_MESMO_TIPO = "decl_constantes_mesmo_tipo";
    public static final String DECL_VARIAVEIS_MESMO_TIPO = "decl_variaveis_mesmo_tipo";
    public static final String TIPO_VARIAVEL = "tipo_variavel";
    public static final String PARAMETRO_REAL = "parametro_real";
    public static final String EXPRESSAO_ARITMETICA = "expressao_aritmetica";
    public static final String COMANDO_GERAL = "comando_geral";
    public static final String COMANDO_LINHA = "comando_linha";
    public static final String COMANDO_BLOCO = "comando_bloco";

    private Janela janela;

    //lista de tokens representando o codigo fonte
    private List<Token> tokens;

    //token atual da análise
    private Token tokenAtual;

    //ponteiro do token atual sendo analisado
    private int ponteiro = -1;

    //lista de erros sintaticos
    private List<ErroSintatico> erros = new ArrayList<ErroSintatico>();

    //classe que armazena os conjuntos primeiros das produções da gramática
    private ConjuntoPrimeiro conjuntoPrimeiro = new ConjuntoPrimeiro();


    public AnalisadorSintatico(Janela janela)
    {
        this.janela = janela;
    }


    //método principal - inicia a análise sintática, dada uma lista de tokens
    
    public void analisar(List<Token> tokens)
    {
        this.tokens = tokens;
        inicializarVariaveis();
        
        proxToken();
        //programa();
        //bloco_constantes();
        //bloco_variaveis();
        //instanciar_obj();
        //classes();
        //expressao();
        //atribuicao();
        comandos();

        if (! (tokenAtual.getTipo() == TokenType.EOF) ) {
            erroSintatico("Token inesperado: " + tokenAtual.getTipo(), tokenAtual.getLinha());
        }
    }

    private void inicializarVariaveis()
    {
        this.ponteiro = -1;
        erros.clear();
    }


//-------- MÉTODOS CORRESPONDENTES AOS NÃO-TERMINAIS DA GRAMÁTICA --------------

//    private void programa()
//    {
//        if (primeiro(BLOCO_CONSTANTES).contains(tokenAtual.getTipo())) {
//            bloco_constantes();
//            //outros_blocos_programa();
//        }
//        else if (primeiro(BLOCO_VARIAVEIS).contains(tokenAtual.getTipo())) {
//            bloco_variaveis();
//            classes();
//        }
//        else if (primeiro(CLASSES).contains(tokenAtual.getTipo())) {
//            classes();
//        }
//    }


    // DECLARAÇÃO DE CONSTANTES

    private void bloco_constantes()
    {
        match(TokenType.CONSTANTES);
        match(TokenType.ABRECHAVE);
        declaracao_constantes();
        match(TokenType.FECHACHAVE);
    }

    private void declaracao_constantes()
    {
        if (primeiro(DECL_CONSTANTES_MESMO_TIPO).contains(tokenAtual.getTipo())) {
            decl_constantes_mesmo_tipo();
            declaracao_constantes();
        }
    }

    private void decl_constantes_mesmo_tipo()
    {
        tipo_variavel();
        lista_decl_constantes();
        match(TokenType.PONTOVIRGULA);
    }

    private void tipo_variavel()
    {
        if ( tokenAtual.getTipo() == TokenType.INTEIRO ||
             tokenAtual.getTipo() == TokenType.REAL ||
             tokenAtual.getTipo() == TokenType.LOGICO ||
             tokenAtual.getTipo() == TokenType.CARACTERE ||
             tokenAtual.getTipo() == TokenType.CADEIA  ) proxToken();
            
        else erroSintatico("<tipo_variavel> esperado; ", tokenAtual.getLinha());
    }

    private void lista_decl_constantes()
    {
        atribuicao();
        loop_lista_decl_constantes();
    }

    private void loop_lista_decl_constantes()
    {
        if (tokenAtual.getTipo() == TokenType.VIRGULA) {
            match(TokenType.VIRGULA);
            lista_decl_constantes();
        }
    }

    // DECLARAÇÃO DE VARIÁVEIS

    private void bloco_variaveis()
    {
        match(TokenType.VARIAVEIS);
        match(TokenType.ABRECHAVE);
        declaracao_variaveis();
        match(TokenType.FECHACHAVE);
    }

    private void declaracao_variaveis()
    {
        if (primeiro(DECL_VARIAVEIS_MESMO_TIPO).contains(tokenAtual.getTipo())) {
            decl_variaveis_mesmo_tipo();
            declaracao_variaveis();
        }
    }

    private void decl_variaveis_mesmo_tipo()
    {
        if(primeiro(TIPO_VARIAVEL).contains(tokenAtual.getTipo())) {
            tipo_variavel();
            lista_decl_variaveis();
            match(TokenType.PONTOVIRGULA);
        }
        else if(tokenAtual.getTipo() == TokenType.ID) {
            match(TokenType.ID);
            match(TokenType.ID);
            complemento_variavel_instanciar_obj();
        }
    }

    private void lista_decl_variaveis()
    {
        match(TokenType.ID);
        complemento_decl_variavel();
    }

    private void complemento_decl_variavel()
    {
        switch( tokenAtual.getTipo() )
        {
            case VIRGULA: {
                loop_lista_decl_variaveis();
                break;
            }
            case ATRIB: {
                match(TokenType.ATRIB);
                segundo_membro_atribuicao();
                loop_lista_decl_variaveis();
                break;
            }
            case ABRECOLCH: {
                match(TokenType.ABRECOLCH);
                expressao_aritmetica();
                match(TokenType.FECHACOLCH);
                break;
            }
            default : erroSintatico("Erro sintático no complemento_decl_variavel!", tokenAtual.getLinha());
        }
    }

    private void loop_lista_decl_variaveis()
    {
        if(tokenAtual.getTipo() == TokenType.VIRGULA) {
            match(TokenType.VIRGULA);
           lista_decl_variaveis();
        }
    }

   

    // DECLARAÇÃO DE CLASSES

    private void classes()
    {
        if(tokenAtual.getTipo() == TokenType.CLASSE) {
           classe();
           classes();
        }
    }

    private void classe()
    {
        match(TokenType.CLASSE);
        match(TokenType.ID);
        complemento_decl_classe();
    }

    private void complemento_decl_classe()
    {
         if(tokenAtual.getTipo() == TokenType.ABRECHAVE) {
           match(TokenType.ABRECHAVE);
           blocos_classe();
           match(TokenType.FECHACHAVE);
        }
         else if(tokenAtual.getTipo() == TokenType.HERDA_DE)
         {
             match(TokenType.HERDA_DE);
             match(TokenType.ID);
             match(TokenType.ABRECHAVE);
             blocos_classe();
             match(TokenType.FECHACHAVE);
         }
         else erroSintatico("Token inesperado: " + tokenAtual.getTipo(), tokenAtual.getLinha());
    }

    private void blocos_classe()
    {
         if(tokenAtual.getTipo() == TokenType.CONSTANTES) {
          bloco_constantes();
          bloco_variaveis();
          bloco_metodos();
        }
    }

    // METODOS

    private void bloco_metodos()
    {
        match(TokenType.METODOS);
        match(TokenType.ABRECHAVE);
        declaracao_metodos();
        metodo_principal();
        match(TokenType.FECHACHAVE);
    }

    private void declaracao_metodos()
    {

    }

    private void metodo_principal()
    {
        
    }

    private void outros_blocos_programa()
    {

    }

    // OBJETOS

    private void instanciar_obj()
    {
        match(TokenType.ID);
        match(TokenType.ID);
        complemento_variavel_instanciar_obj();
    }

    private void complemento_variavel_instanciar_obj()
    {
        if(tokenAtual.getTipo() == TokenType.ABREPAR) {
            match(TokenType.ABREPAR);
           parametros_reais_instanciar_obj();
           match(TokenType.FECHAPAR);
        }
    }

    private void parametros_reais_instanciar_obj()
    {
        parametro_real();
        loop_parametros_reais();
    }

    private void parametro_real()
    {
        if ( tokenAtual.getTipo() == TokenType.ID ||
             tokenAtual.getTipo() == TokenType.NUM ||
             tokenAtual.getTipo() == TokenType.LITERAL ||
             tokenAtual.getTipo() == TokenType.CARACTER ||
             tokenAtual.getTipo() == TokenType.VERDADEIRO ||
             tokenAtual.getTipo() == TokenType.FALSO  ) proxToken();

        else erroSintatico("<parametro_real> esperado; ", tokenAtual.getLinha());
    }

    private void loop_parametros_reais()
    {
        if(tokenAtual.getTipo() == TokenType.VIRGULA) {
            match(TokenType.VIRGULA);
            parametros_reais_instanciar_obj();
        }
    }

    // EXPRESSÕES

    private void expressao()
    {
        if(tokenAtual.getTipo() == TokenType.ABREPAR) {
            match(TokenType.ABREPAR);
            expressao_parentese();
        }
        else if(tokenAtual.getTipo() == TokenType.ID || tokenAtual.getTipo() == TokenType.NUM ) {
            termo();
            prox_trecho_soma();
            prox_trecho_relacional();
        }
        else if(tokenAtual.getTipo() == TokenType.VERDADEIRO || tokenAtual.getTipo() == TokenType.FALSO ) {
            expressao_booleana();
            prox_trecho_expl();
        }
        else erroSintatico("Token inesperado: " + tokenAtual.getTipo(), tokenAtual.getLinha());

    }

    private void expressao_parentese()
    {
        if(tokenAtual.getTipo() == TokenType.ABREPAR) {
            match(TokenType.ABREPAR);
            complemento_expressao_parentese();
        }
        else if(tokenAtual.getTipo() == TokenType.ID || tokenAtual.getTipo() == TokenType.NUM ) {
            termo();
            prox_trecho_soma();
            prox_trecho_geral();
        }
        else if(tokenAtual.getTipo() == TokenType.VERDADEIRO || tokenAtual.getTipo() == TokenType.FALSO ) {
            expressao_booleana();
            prox_trecho_expl();
            match(TokenType.FECHAPAR);
        }
        else erroSintatico("Token inesperado: " + tokenAtual.getTipo(), tokenAtual.getLinha());
    }

    private void termo()
    {
        fator();
        prox_trecho_multiplicacao();
    }

    private void fator()
    {
         if(tokenAtual.getTipo() == TokenType.ID) {
            match(TokenType.ID);
            complemento_fator_variavel();
        }
        else if(tokenAtual.getTipo() == TokenType.NUM ) {
            match(TokenType.NUM);
        }
        else erroSintatico("Token inesperado: " + tokenAtual.getTipo(), tokenAtual.getLinha());
    }

    private void complemento_fator_variavel()
    {
        if(tokenAtual.getTipo() == TokenType.ABREPAR || tokenAtual.getTipo() == TokenType.PONTO || tokenAtual.getTipo() == TokenType.ATRIB) {
            loop_acesso_atributo_obj();
        }
        else if(tokenAtual.getTipo() == TokenType.ABRECOLCH) {
            match(TokenType.ABRECOLCH);
            expressao_aritmetica();
            match(TokenType.FECHACOLCH);
        }
    }

    private void prox_trecho_multiplicacao()
    {
        if(tokenAtual.getTipo() == TokenType.MULT || tokenAtual.getTipo() == TokenType.DIV) {
            operador_multiplicacao();
            expressao_aritmetica();
        }
    }

    private void operador_multiplicacao()
    {
        if ( tokenAtual.getTipo() == TokenType.MULT ||
             tokenAtual.getTipo() == TokenType.DIV  ) proxToken();

        else erroSintatico("<operador_multiplicacao> esperado; ", tokenAtual.getLinha());
    }

    private void prox_trecho_soma()
    {
        if(tokenAtual.getTipo() == TokenType.ADICAO || tokenAtual.getTipo() == TokenType.SUB) {
            operador_soma();
            expressao_aritmetica();
        }
    }

    private void operador_soma()
    {
        if ( tokenAtual.getTipo() == TokenType.ADICAO ||
             tokenAtual.getTipo() == TokenType.SUB  ) proxToken();

        else erroSintatico("<operador_soma> esperado; ", tokenAtual.getLinha());
    }

    private void prox_trecho_relacional()
    {
        if ( tokenAtual.getTipo() == TokenType.MAIOR ||
             tokenAtual.getTipo() == TokenType.MENOR ||
             tokenAtual.getTipo() == TokenType.MAIORIGUAL   ||
             tokenAtual.getTipo() == TokenType.MENORIGUAL ||
             tokenAtual.getTipo() == TokenType.IGUAL ||
             tokenAtual.getTipo() == TokenType.DIF  ) {
            
                operador_relacional();
                expressao_aritmetica();
                prox_trecho_expl();
        }
    }

    private void operador_relacional()
    {
        if ( tokenAtual.getTipo() == TokenType.MAIOR ||
             tokenAtual.getTipo() == TokenType.MENOR ||
             tokenAtual.getTipo() == TokenType.MAIORIGUAL   ||
             tokenAtual.getTipo() == TokenType.MENORIGUAL ||
             tokenAtual.getTipo() == TokenType.IGUAL ||
             tokenAtual.getTipo() == TokenType.DIF  ) proxToken();

        else erroSintatico("<operador_relacional> esperado; ", tokenAtual.getLinha());
    }

    private void prox_trecho_expl()
    {
         if ( tokenAtual.getTipo() == TokenType.OU || tokenAtual.getTipo() == TokenType.E  ) {
             operador_logico();
             expressao_logica();
         }
    }

    private void operador_logico()
    {
        if ( tokenAtual.getTipo() == TokenType.OU ||
             tokenAtual.getTipo() == TokenType.E  ) proxToken();
    }

    private void expressao_logica()
    {
        termo_l();
        prox_trecho_expl();
    }

    private void termo_l()
    {
        if ( tokenAtual.getTipo() == TokenType.VERDADEIRO || tokenAtual.getTipo() == TokenType.FALSO  ) {
             expressao_booleana();
         }
        else if ( tokenAtual.getTipo() == TokenType.ABREPAR ) {
             match(TokenType.ABREPAR);
             termo_l_parentesis();
         }
        else if ( tokenAtual.getTipo() == TokenType.ID || tokenAtual.getTipo() == TokenType.NUM  ) {
             termo();
             prox_trecho_soma();
             operador_relacional();
             expressao_aritmetica();
         }
        else erroSintatico("Token inesperado: " + tokenAtual.getTipo(), tokenAtual.getLinha());
    }

    private void termo_l_parentesis()
    {
        if(primeiro(EXPRESSAO_ARITMETICA).contains(tokenAtual.getTipo())) {
            expressao_aritmetica();
            match(TokenType.FECHAPAR);
            operador_relacional();
            expressao_aritmetica();
        }
        else if( tokenAtual.getTipo() == TokenType.E || tokenAtual.getTipo() == TokenType.OU  ) {
            prox_trecho_expl();
            match(TokenType.FECHAPAR);
        }
    }



    private void complemento_expressao_parentese()
    {
        termo_l_parentesis();
        prox_trecho_expl();
        match(TokenType.FECHAPAR);
    }

    private void prox_trecho_geral()
    {
        if ( tokenAtual.getTipo() == TokenType.MAIOR ||
             tokenAtual.getTipo() == TokenType.MENOR ||
             tokenAtual.getTipo() == TokenType.MAIORIGUAL   ||
             tokenAtual.getTipo() == TokenType.MENORIGUAL ||
             tokenAtual.getTipo() == TokenType.IGUAL ||
             tokenAtual.getTipo() == TokenType.DIF  ) {
            operador_relacional();
            expressao_aritmetica();
            prox_trecho_expl();
            match(TokenType.FECHAPAR);
        }
        else if( tokenAtual.getTipo() == TokenType.FECHAPAR ) {
            match(TokenType.FECHAPAR);
            complemento_exp_aritm_parentese();
        }
        else erroSintatico("Token inesperado: " + tokenAtual.getTipo(), tokenAtual.getLinha());
    }

    private void complemento_exp_aritm_parentese()
    {
        if ( tokenAtual.getTipo() == TokenType.MAIOR ||
             tokenAtual.getTipo() == TokenType.MENOR ||
             tokenAtual.getTipo() == TokenType.MAIORIGUAL   ||
             tokenAtual.getTipo() == TokenType.MENORIGUAL ||
             tokenAtual.getTipo() == TokenType.IGUAL ||
             tokenAtual.getTipo() == TokenType.DIF  ) {
            operador_relacional();
            expressao_aritmetica();
            prox_trecho_expl();
        }
    }

    private void expressao_aritmetica()
    {
        if ( tokenAtual.getTipo() == TokenType.ID || tokenAtual.getTipo() == TokenType.NUM  ) {
             termo();
             prox_trecho_soma();
         }
        else if( tokenAtual.getTipo() == TokenType.ABREPAR) {
            match( TokenType.ABREPAR );
            expressao_aritmetica();
            match( TokenType.FECHAPAR );
        }
        else erroSintatico("Token inesperado: " + tokenAtual.getTipo(), tokenAtual.getLinha());
    }

    private void expressao_booleana()
    {
        if ( tokenAtual.getTipo() == TokenType.VERDADEIRO || tokenAtual.getTipo() == TokenType.FALSO  ) {
             proxToken();
         }
    }


    // COMANDOS

    private void comandos()
    {
        if(primeiro(COMANDO_GERAL).contains(tokenAtual.getTipo())) {
            comando_geral();
            comandos();
        }
    }

    private void comando_geral()
    {
        if(primeiro(COMANDO_LINHA).contains(tokenAtual.getTipo())) {
            comando_linha();
            match(TokenType.PONTOVIRGULA);
        }
        else if (primeiro(COMANDO_BLOCO).contains(tokenAtual.getTipo())) {
            comando_bloco();
        }
        else erroSintatico("Token inesperado em comando_geral: " + tokenAtual.getTipo(), tokenAtual.getLinha());
    }

    private void comando_linha()
    {
        switch(tokenAtual.getTipo())
        {
            case ESCREVA: {
                comando_escreva();
                break;
            }
            case LEIA: {
                comando_leia();
                break;
            }
            case RETORNO: {
                retorno();
                break;
            }
            case INCR: {
                incremento_decremento();
                match(TokenType.ID);
                break;
            }
            case DECR: {
                incremento_decremento();
                match(TokenType.ID);
                break;
            }
            case ID: {
                match(TokenType.ID);
                complemento_variavel_comando();
                break;
            }
            default: erroSintatico("Token inesperado: " + tokenAtual.getTipo(), tokenAtual.getLinha());
        }
    }

    private void comando_bloco()
    {
        switch(tokenAtual.getTipo())
        {
             case SE: {
                comando_se();
                break;
            }
            case PARA: {
                comando_para();
                break;
            }
            case ENQUANTO: {
                comando_enquanto();
                break;
            }
         }
    }
    
    private void comando_se()
    {
        match(TokenType.SE);
        match(TokenType.ABREPAR);
        expressao_logica();
        match(TokenType.FECHAPAR);
        match(TokenType.ENTAO);
        match(TokenType.ABRECHAVE);
        comandos();
        match(TokenType.FECHACHAVE);
        complemento_comando_se();
    }
    
    private void complemento_comando_se()
    {
        if(tokenAtual.getTipo() == TokenType.SENAO) {
            match(TokenType.SENAO);
            match(TokenType.ABRECHAVE);
            comandos();
            match(TokenType.FECHACHAVE);
        }
    }
    
    private void comando_para()
    {
        match(TokenType.PARA);
        match(TokenType.ABREPAR);
        atribuicao();
        match(TokenType.PONTOVIRGULA);
        expressao_logica();
        match(TokenType.PONTOVIRGULA);
        atribuicao();
        match(TokenType.ABRECHAVE);
        comandos();
        match(TokenType.FECHACHAVE);
    }
    
    private void comando_enquanto()
    {
        match(TokenType.ENQUANTO);
        match(TokenType.ABREPAR);
        expressao_logica();
        match(TokenType.FECHAPAR);
        match(TokenType.ABRECHAVE);
        comandos();
        match(TokenType.FECHACHAVE);
    }

    private void comando_escreva()
    {
        match(TokenType.ESCREVA);
        match(TokenType.ABREPAR);
        params_escreva();
        match(TokenType.FECHAPAR);
    }

    private void params_escreva()
    {
        param_escreva();
        loop_params_escreva();
    }

    private void param_escreva()
    {
        if ( tokenAtual.getTipo() == TokenType.ABREPAR ||
             tokenAtual.getTipo() == TokenType.VERDADEIRO ||
             tokenAtual.getTipo() == TokenType.FALSO   ||
             tokenAtual.getTipo() == TokenType.ID ||
             tokenAtual.getTipo() == TokenType.NUM  ) {

            expressao();
        }
        else if ( tokenAtual.getTipo() == TokenType.LITERAL ) {
            match(TokenType.LITERAL);
        }
        else erroSintatico("Token inesperado: " + tokenAtual.getTipo(), tokenAtual.getLinha());
    }

    private void loop_params_escreva()
    {
        if ( tokenAtual.getTipo() == TokenType.VIRGULA ) {
            match(TokenType.VIRGULA);
            params_escreva();
        }
    }

    private void comando_leia()
    {
        match(TokenType.LEIA);
        match(TokenType.ABREPAR);
        params_leia();
        match(TokenType.FECHAPAR);
    }

    private void params_leia()
    {
        match(TokenType.ID);
        loop_params_leia();
    }

    private void loop_params_leia()
    {
        if ( tokenAtual.getTipo() == TokenType.VIRGULA ) {
            match(TokenType.VIRGULA);
            params_leia();
        }
    }

    private void retorno()
    {
        match(TokenType.RETORNO);
        match(TokenType.ABREPAR);
        expressao();
        match(TokenType.FECHAPAR);
    }

    private void complemento_variavel_comando()
    {
        switch(tokenAtual.getTipo())
        {
            case PONTO: {
                match(TokenType.PONTO);
                complemento_ponto_comando();
                break;
            }
            case ABRECOLCH: {
                match(TokenType.ABRECOLCH);
                expressao_aritmetica();
                match(TokenType.FECHACOLCH);
                match(TokenType.ATRIB);
                segundo_membro_atribuicao();
                break;
            }
            case ATRIB: {
                match(TokenType.ATRIB);
                segundo_membro_atribuicao();
                break;
            }
            case ABREPAR: {
                match(TokenType.ABREPAR);
                parametros_reais();
                match(TokenType.FECHAPAR);
                break;
            }
            case INCR: {
                incremento_decremento();
                break;
            }
            case DECR: {
                incremento_decremento();
                break;
            }
        }
    }

    private void complemento_ponto_comando()
    {
        match(TokenType.ID);
        loop_acesso_atributo_obj();
    }

    private void parametros_reais()
    {
        if (primeiro(PARAMETRO_REAL).contains(tokenAtual.getTipo())) {
            parametro_real();
            loop_parametros_reais();
        }
    }

    private void loop_acesso_atributo_obj()
    {
        switch(tokenAtual.getTipo())
        {
            case PONTO: {
                match(TokenType.PONTO);
                complemento_ponto_comando();
                break;
            }
            case ABREPAR: {
                match(TokenType.ABREPAR);
                parametros_reais();
                match(TokenType.FECHAPAR);
                break;
            }
            case ATRIB: {
                match(TokenType.ATRIB);
                segundo_membro_atribuicao();
                break;
            }
            default: erroSintatico("Token inesperado: " + tokenAtual.getTipo(), tokenAtual.getLinha());
        }
    }

    // ATRIBUIÇÃO

    private void atribuicao()
    {
        if ( tokenAtual.getTipo() == TokenType.ID  ) {
            match( TokenType.ID );
            complemento_id_atribuicao();
         }
        else if( tokenAtual.getTipo() == TokenType.INCR || tokenAtual.getTipo() == TokenType.DECR ) {
            incremento_decremento();
            match( TokenType.ID );
        }
        else erroSintatico("Token inesperado: " + tokenAtual.getTipo(), tokenAtual.getLinha());
    }

    private void complemento_id_atribuicao()
    {
        if(tokenAtual.getTipo() == TokenType.ABREPAR ||
           tokenAtual.getTipo() == TokenType.PONTO ||
           tokenAtual.getTipo() == TokenType.ATRIB ||
           tokenAtual.getTipo() == TokenType.ABRECOLCH)  {

            complemento_referencia_variavel();
            match(TokenType.ATRIB);
            segundo_membro_atribuicao();
        }
        else if(tokenAtual.getTipo() == TokenType.INCR ||
                tokenAtual.getTipo() == TokenType.DECR) {

            incremento_decremento();
        }
    }

     private void segundo_membro_atribuicao()
     {
         switch(tokenAtual.getTipo())
         {
             case ID: {
                match(TokenType.ID);
                complemento_variavel_atribuicao();
                break;
             }
             case NUM: {
                match(TokenType.NUM);
                prox_trecho_multiplicacao();
                prox_trecho_soma();
                break;
             }
             case ABREPAR: {
                match(TokenType.ABREPAR);
                expressao_aritmetica();
                match(TokenType.FECHAPAR);
                break;
             }
             case INCR: {
                incremento_decremento();
                match(TokenType.ID);
                break;
             }
             case DECR: {
                incremento_decremento();
                match(TokenType.ID);
                break;
             }
             case VERDADEIRO: {
                expressao_booleana();
                break;
             }
             case FALSO: {
                expressao_booleana();
                break;
             }
             case LITERAL: {
                match(TokenType.LITERAL);
                break;
             }
             case CARACTER: {
                match(TokenType.CARACTER);
                break;
             }
             default: erroSintatico("Token inesperado: " + tokenAtual.getTipo(), tokenAtual.getLinha());
         }
     }

     private void complemento_variavel_atribuicao()
     {
         if(tokenAtual.getTipo() == TokenType.ABREPAR ||
           tokenAtual.getTipo() == TokenType.PONTO ||
           tokenAtual.getTipo() == TokenType.ATRIB ||
           tokenAtual.getTipo() == TokenType.ABRECOLCH)  {

             complemento_fator_variavel();
             prox_trecho_multiplicacao();
             prox_trecho_soma();
         }
         else if(tokenAtual.getTipo() == TokenType.INCR || tokenAtual.getTipo() == TokenType.DECR ) {
             incremento_decremento();
         }
     }

    private void incremento_decremento()
    {
        if(tokenAtual.getTipo() == TokenType.INCR || tokenAtual.getTipo() == TokenType.DECR) proxToken();
        else erroSintatico("<incremento_decremento> esperado: " + tokenAtual.getTipo(), tokenAtual.getLinha());
    }

    private void complemento_referencia_variavel()
    {
        if(tokenAtual.getTipo() == TokenType.ABREPAR ||
           tokenAtual.getTipo() == TokenType.PONTO ||
           tokenAtual.getTipo() == TokenType.ATRIB) {
            loop_acesso_atributo_obj();
        }
        else if(tokenAtual.getTipo() == TokenType.ABRECOLCH) {
            match(TokenType.ABRECOLCH);
            expressao_aritmetica();
            match(TokenType.FECHACOLCH);
        }
    }

//-------- MÉTODO QUE RETORNA O CONJUNTO PRIMEIRO DE UMA DADA PRODUÇÃO ---------

    private Set<TokenType> primeiro(String producao)
    {
        return conjuntoPrimeiro.getConjunto(producao);
    }

//--------------------------- MÉTODOS AUXILIARES -------------------------------

    private void proxToken()
    {
        ponteiro++;
        tokenAtual = tokens.get(ponteiro);
    }

    private void match(TokenType esperado)
    {
        if(tokenAtual.getTipo() == esperado) proxToken();
        else erroSintatico("Token inesperado: " + tokenAtual.getTipo() + ", esperava: " + esperado, tokenAtual.getLinha());
    }

    private void erroSintatico(String msg, int linha)
    {
        erros.add(new ErroSintatico(msg, linha));
    }

    public void imprimirSaida()
    {
        if (temErros()) janela.imprimirCabecalhoErrosSintaticos();

        for (ErroSintatico erro : erros) {
            janela.imprimirErroSintatico(erro);
        }

        //if (temErros()) janela.imprimirTotalDeErrosSintaticos(erros.size());
    }

    public boolean temErros()
    {
        if( erros==null ) return false;
        return erros.size() > 0;
    }

    public int getErros()
    {
        return erros.size();
    }

}