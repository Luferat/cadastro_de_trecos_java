package net.luferat.cadastro_de_trecos.crud;

import net.luferat.cadastro_de_trecos.setup.AppSetup;

public class Create extends AppSetup {

    public static void create() {

        String sql = "";

        try {

            // Prepara interface (view).
            System.out.println(appName + "\n" + line);
            System.out.println("Novo registro");
            System.out.println(line + "\n");
            
            System.out.println("Preencha os dados com atenção!");
            System.out.println("\tNome: ");
            String strName = scanner.nextLine();
            System.out.println("\n\tDescrição: ");
            String strDescription = scanner.nextLine().trim();
            System.out.print("\n\nOs dados estão corretos? [y/N] ");
            String confirm = scanner.next();

        } catch (Exception e) {
        }

    }

}
