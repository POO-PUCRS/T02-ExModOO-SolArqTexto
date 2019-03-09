
public class App{
    public static void imprimeDados(CadastroClientes cc,String titulo,int indices[]){
        System.out.println(titulo+":");
        for(int i=0;i<indices.length;i++){
            int ind = indices[i];
            String linha = "Cliente ("+ind+"):"+
                           cc.get("nome",ind)+", "+
                           cc.get("email",ind)+", "+
                           cc.get("sexo",ind)+", "+
                           cc.get("telefone",ind)+", "+
                           cc.get("cep",ind)+", "+
                           cc.get("cidade",ind)+", "+
                           cc.get("pais",ind)+", "+
                           cc.get("profissao",ind)+".";
            System.out.println(linha);
        }
        System.out.println("----------\n");
    }

    public static void main(String args[]) {
        CadastroClientes cc = new CadastroClientes();

        cc.carregaDados("Clientes.csv");
        imprimeDados(cc,"Todos os clientes",cc.getTodosClientes());
        imprimeDados(cc,"Clientes homens",cc.getClientesPorSexo("Male"));
        imprimeDados(cc,"Clientes mulheres",cc.getClientesPorSexo("Female"));
    }
}