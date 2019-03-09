import java.util.Scanner;
import java.nio.file.*;
import java.nio.charset.*;
import java.io.*;
import java.util.Arrays;

public class CadastroClientes{
    private final int MAX_DADOS = 1000;
    private boolean dadosOK;
    private String nome[];
    private String email[];
    private String sexo[];
    private String telefone[];
    private String cep[];
    private String cidade[];
    private String pais[];
    private String profissao[];

    public CadastroClientes(){
        nome = new String[MAX_DADOS];
        email = new String[MAX_DADOS];
        sexo = new String[MAX_DADOS];
        telefone = new String[MAX_DADOS];
        cep  = new String[MAX_DADOS];
        cidade  = new String[MAX_DADOS];
        pais = new String[MAX_DADOS];
        profissao = new String[MAX_DADOS];

        dadosOK = false;
    }

    public boolean getDadosOk(){
        return dadosOK;
    }

    public void carregaDados(String nomeArquivo){
        // Monta string com nome absoluto do arquivo
        String dirCorrente = Paths.get("").toAbsolutePath().toString();
        String nomeCompleto = dirCorrente+"\\"+nomeArquivo;

        // Define caminho até o arquivo físico a partir do nome absoluto
        Path path = Paths.get(nomeCompleto);

        // Usa a classe Scanner para ler o arquivo
        int ind = 0;
        try (Scanner sc = new Scanner(Files.newBufferedReader(path, Charset.forName("UTF-8")))){
            // Consome a linha do cabecalho
            sc.nextLine();
            while(sc.hasNext()){
                String line = sc.nextLine();
                String campos[] = line.split("[,\n]");
                // Atenção: campos[0] corresponde ao codigo (equivalente a posicao do arranjo)
                nome[ind] = campos[1].trim();
                email[ind] = campos[2].trim();
                sexo[ind] = campos[3].trim();
                telefone[ind] = campos[4].trim(); 
                cep[ind]  = campos[5].trim();
                cidade[ind] = campos[6].trim();
                pais[ind] = campos[7].trim();
                profissao[ind] = campos[8].trim();
                ind++;
            }
            dadosOK = true;
        }catch (IOException x){
            System.err.format("Erro de E/S: %s%n", x);
            dadosOK = false;
        }
    }

    public String get(String campo,int indice){
        if (!dadosOK || indice<0 || indice >= MAX_DADOS){
            return null;
        }
        switch(campo){
            case "nome":return nome[indice];
            case "email":return email[indice];
            case "sexo":return sexo[indice];
            case "telefone":return telefone[indice];
            case "cep":return cep[indice];
            case "cidade":return cidade[indice];
            case "pais":return pais[indice];
            case "profissao":return profissao[indice];
            default: return "none";         
        }
    }

    public int[] getTodosClientes(){
        if (!dadosOK){
            return null;
        }
        int[] resp = new int[MAX_DADOS];
        for(int i=0;i<MAX_DADOS;i++){
            resp[i] = i;
        }
        return resp;
    }

    public int[] getClientesPorSexo(String sexo){
        if (!dadosOK){
            return null;
        }
        int[] resp = new int[MAX_DADOS];
        int pos = 0;
        for(int i=0;i<MAX_DADOS;i++){
            if (this.sexo[i].equals(sexo)){
                resp[pos] = i;
                pos++;
            }
        }
        return Arrays.copyOf(resp, pos);
    }

    /* Exercícios:
     1) Clientes por cidade
     2) Clientes por pais
     3) Clientes por pais e profissão
     */
}