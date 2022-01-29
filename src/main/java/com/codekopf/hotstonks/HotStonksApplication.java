package com.codekopf.hotstonks;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.codekopf.hotstonks.file.Template;
import com.codekopf.hotstonks.service.FinvizScrapper;
import com.codekopf.hotstonks.service.MenuScraper;

public class HotStonksApplication {

    public static final String DOWNLOAD_FILE_PATH = "C:\\hotstonks\\";
    //    public static final String DOWNLOAD_FILE_PATH = "C:\\Users\\miroslav.staffa\\Documents\\Lunchtime\\";

    public static void main(String[] args) throws IOException {

        LocalDate today = LocalDate.now().atStartOfDay().toLocalDate();
        String date = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(today);

        // Init WebDriver
//        Arrays.asList(System.getProperties()).stream().forEach(p -> {System.out.println(p);});
//        System.out.println();
        System.setProperty("webdriver.gecko.driver", "C:\\bin\\geckodriver\\geckodriver.exe");

        // backupPrevious();

        // Industry - do

        List<String> ownedStocks = new ArrayList<>();
        // Degiro
        ownedStocks.add("AMD");    // Advanced Micro Devices, Inc.
        // iOwn.add("");    Airbus
        ownedStocks.add("ALK");    // Alaska Air Group, Inc.
        ownedStocks.add("AAL");    // American Airlines Group Inc.
        ownedStocks.add("T");      // AT&T Inc.
        ownedStocks.add("BP");     // BP p.l.c.
        // iOwn.add("");    CEZ as
        // iOwn.add("");    Deutsche Lufthansa AG
        // iOwn.add("");    Erste Group Bank AG - https://www.google.com/finance/quote/EBS:VIE?sa=X&window=5Y ; https://www.boersen-zeitung.de/unternehmen/kurse/AT0000652011
        ownedStocks.add("F");      // Ford Motor
        ownedStocks.add("FNKO");   // Funko Inc
        ownedStocks.add("GM");     // General Motors Co
        ownedStocks.add("ING");    // ING Groep NV
        // iOwn.add("");    Kofola CeskoSlovensko as
        ownedStocks.add("KHC");    // Kraft Heinz Co
        ownedStocks.add("M");      // Macy's Inc
        // iOwn.add("");    Moneta Money Bank as
        ownedStocks.add("NCLH");   // Norwegian Cruise Line Holdings Ltd
        ownedStocks.add("PLUG");   // Plug Power Inc
        ownedStocks.add("RDS-A");  // Royal Dutch Shell plc - EAM a PLC ?????
        // iOwn.add("");    RWE AG
        // iOwn.add("");    // Schibsted ASA
        ownedStocks.add("SIX");    // Six Flags Entertainment Corp
        ownedStocks.add("LUV");    // Southwest Airlines
        // iOwn.add("");    Stock Spirits Group PLC
        // iOwn.add("");    thyssenkrupp AG
        ownedStocks.add("UL");     // Unilever PLC
        // iOwn.add("");    Volkswagen AG
        ownedStocks.add("WFC");    // Wells Fargo
        // Revolut
        ownedStocks.add("CSCO");   // Cisco
        ownedStocks.add("KO");     // The Coca-Cola Company
        ownedStocks.add("MSFT");   // Microsoft Corporation
        ownedStocks.add("AMZN");   // Amazon.com, Inc.
        ownedStocks.add("GOOGL");  // Alphabet Inc.
        ownedStocks.add("MCD");    // McDonald's Corporation
        // Trading212
        ownedStocks.add("CNI");    // Canadian National Railway
        ownedStocks.add("CP");     // Canadian Pacific Railway Limited
        ownedStocks.add("CSX");    // CSX Corporation
        ownedStocks.add("KNL");    // Knoll, Inc.                   // TODO No results found for KNL
        ownedStocks.add("NSC");    // Norfolk Southern Corporation
        // iOwn.add("");        Orsted
        ownedStocks.add("UNP");    // Union Pacific Corporation



        List<String> speculativeStocks = new ArrayList<>();
        speculativeStocks.add("ARKK");    // ARK Innovation ETF
        speculativeStocks.add("TSLA");    // Tesla
        speculativeStocks.add("ABNB");    // AirBnB
        speculativeStocks.add("TSN");     // Tyson Foods, Inc.
        speculativeStocks.add("X");       // United States Steel
        speculativeStocks.add("KR");      // Kroger Co.
        speculativeStocks.add("APPL");    // Apple Inc         // TODO multiple results
        speculativeStocks.add("NFLX");    // Netflix Inc.
        speculativeStocks.add("NESN");    // Nestlé S.A.
        speculativeStocks.add("TWLO");    // Twilio Inc.
        speculativeStocks.add("SGRE");    // Siemens Gamesa Renewable Energy SA
        speculativeStocks.add("DOCN");    // DigitalOcean Holdings Inc
        speculativeStocks.add("CRM");     // Salesforce
        speculativeStocks.add("GOLD");    // Barrick Gold Corp
        speculativeStocks.add("NYSE");    // Sony Corporation NYSE: SNE
        speculativeStocks.add("B4B");     // Metro AG
        speculativeStocks.add("LKNCY");   // luckin coffee
        speculativeStocks.add("BA");      // Boeing

        // STARBACKS pridat do toho co pozeram

        List<MenuScraper> menuScrapers = new ArrayList<>();

        ownedStocks.forEach(ownedStock -> menuScrapers.add(new FinvizScrapper(ownedStock, ownedStock, date)));
        speculativeStocks.forEach(speculativeStock -> menuScrapers.add(new FinvizScrapper(speculativeStock, speculativeStock, date)));

//        menuScrapers.add(new MenickaMenuScraper("beefhouse-bistro", "5932-beefhouse-bistro--coffee.html"));
//        menuScrapers.add(new MenickaMenuScraper("beefhouse-grill-bar", "5931-beefhouse-grill--bar.html"));
//        menuScrapers.add(new MenickaMenuScraper("chilli-point", "4033-chilli-point.html"));
//        menuScrapers.add(new ImageMenuScraper("indian", "C:\\Lunchtime\\indian.png"));
//        menuScrapers.add(new MenickaMenuScraper("la-corte", "1834-la-corte--dvur-restaurant.html"));
//        menuScrapers.add(new MenickaMenuScraper("la-ventola", "294-pizzeria-la-ventola.html"));
//        menuScrapers.add(new MenickaMenuScraper("las-adelitas", "4575-las-adelitas---male-namesti.html"));
//        menuScrapers.add(new MenickaMenuScraper("merkur", "4545-cafe-merkur.html"));
//        menuScrapers.add(new ZomatoMenuScraper("opera", "restaurace-hotel-opera-nov%C3%A9-m%C4%9Bsto-praha-1/denn%C3%AD-menu"));
//        menuScrapers.add(new PivolodMenuScraper());
//        menuScrapers.add(new RestuMenuScraper("sia", "sia-restaurant/menu"));
//        menuScrapers.add(new SklepMenuScraper());
//        menuScrapers.add(new ZomatoMenuScraper("tanker", "t-anker-star%C3%A9-m%C4%9Bsto-praha-1/denn%C3%AD-menu"));
//        menuScrapers.add(new MenickaMenuScraper("tesnov", "4880-lidova-jidelna-tesnov.html"));

        File f = new File(DOWNLOAD_FILE_PATH + date);
        f.mkdirs();
        f = new File(DOWNLOAD_FILE_PATH + date + "\\index.html");
        BufferedWriter bw = new BufferedWriter(new FileWriter(f));


        // tabs - https://getbootstrap.com/docs/5.0/components/navs-tabs/#tabs

        // view-source:https://getbootstrap.com/docs/5.0/examples/headers/

//        <div class="container">
//    <header class="d-flex flex-wrap justify-content-center py-3 mb-4 border-bottom">
//      <a href="/" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-dark text-decoration-none">
//        <svg class="bi me-2" width="40" height="32"><use xlink:href="#bootstrap"/></svg>
//        <span class="fs-4">Simple header</span>
//      </a>
//
//      <ul class="nav nav-pills">
//        <li class="nav-item"><a href="#" class="nav-link active" aria-current="page">Home</a></li>
//        <li class="nav-item"><a href="#" class="nav-link">Features</a></li>
//        <li class="nav-item"><a href="#" class="nav-link">Pricing</a></li>
//        <li class="nav-item"><a href="#" class="nav-link">FAQs</a></li>
//        <li class="nav-item"><a href="#" class="nav-link">About</a></li>
//      </ul>
//    </header>
//  </div>



//
//    <!-- Custom styles for this template -->
//    <link href="headers.css" rel="stylesheet">
//
//      <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
//        <a href="/" class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none">
//          <svg class="bi me-2" width="40" height="32" role="img" aria-label="Bootstrap"><use xlink:href="#bootstrap"/></svg>
//        </a>
//
//        <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
//          <li><a href="#" class="nav-link px-2 text-secondary">Home</a></li>
//          <li><a href="#" class="nav-link px-2 text-white">Features</a></li>
//          <li><a href="#" class="nav-link px-2 text-white">Pricing</a></li>
//          <li><a href="#" class="nav-link px-2 text-white">FAQs</a></li>
//          <li><a href="#" class="nav-link px-2 text-white">About</a></li>
//        </ul>
//
//        <form class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3">
//          <input type="search" class="form-control form-control-dark" placeholder="Search..." aria-label="Search">
//        </form>
//
//        <div class="text-end">
//          <button type="button" class="btn btn-outline-light me-2">Login</button>
//          <button type="button" class="btn btn-warning">Sign-up</button>
//        </div>
//      </div>


        StringBuilder content = new StringBuilder();

        content.append("<div class=\"tab-content\" id=\"nav-tabContent\">");
        // All
        content.append("<div class=\"tab-pane fade show active\" id=\"nav-all\" role=\"tabpanel\" aria-labelledby=\"nav-all-tab\">");

        content.append("<h1>All Stocks!</h1>");
        content.append("<h2>Owned stocks!</h2>");
        ownedStocks.forEach(ownedStock -> content.append("<img src=\"" +  ownedStock + "-d.png\"><br><br><img src=\"" +  ownedStock + "-w.png\"><br><br>"));
        content.append("<h2>Speculative stocks!</h2>");
        speculativeStocks.forEach(speculativeStock -> content.append("<img src=\"" +  speculativeStock + "-d.png\"><br><br><img src=\"" +  speculativeStock + "-w.png\"><br><br>"));

        content.append("</div>");

        // Daily
        content.append("<div class=\"tab-pane fade\" id=\"nav-daily\" role=\"tabpanel\" aria-labelledby=\"nav-daily-tab\">");

        content.append("<h1>Daily</h1>");
        content.append("<h2>Owned stocks!</h2>");
        ownedStocks.forEach(ownedStock -> content.append("<img src=\"" +  ownedStock + "-d.png\"><br><br>"));
        content.append("<h2>Speculative stocks!</h2>");
        speculativeStocks.forEach(speculativeStock -> content.append("<img src=\"" +  speculativeStock + "-d.png\"><br><br>"));

        content.append("</div>");

        // Weekly
        content.append("<div class=\"tab-pane fade\" id=\"nav-weekly\" role=\"tabpanel\" aria-labelledby=\"nav-weekly-tab\">");

        content.append("<h1>Weekly</h1>");
        content.append("<h2>Owned stocks!</h2>");
        ownedStocks.forEach(ownedStock -> content.append("<img src=\"" +  ownedStock + "-w.png\"><br><br>"));
        content.append("<h2>Speculative stocks!</h2>");
        speculativeStocks.forEach(speculativeStock -> content.append("<img src=\"" +  speculativeStock + "-w.png\"><br><br>"));

        content.append("</div>");
        content.append("</div>");



        Template html = new Template(date);
        html.setContent(content.toString());
        bw.write(html.build());
        bw.close();

        menuScrapers.forEach(menuScraper -> {
            try {
                menuScraper.getMenu();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // TODO merat cas a statistiky ako rychlo to spravi
    }

    private static void backupPrevious() {
        File dir = new File(DOWNLOAD_FILE_PATH);
        for (File child : Objects.requireNonNull(dir.listFiles())) {
            try {
                if (!child.isDirectory()) {
                    Path target = Paths.get((DOWNLOAD_FILE_PATH + "backup\\") + child.getName());
                    target.toFile().getParentFile().mkdirs();
                    Files.move(child.toPath(), target, StandardCopyOption.REPLACE_EXISTING);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
