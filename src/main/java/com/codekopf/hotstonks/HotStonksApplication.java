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
import com.codekopf.hotstonks.model.StockTitle;
import com.codekopf.hotstonks.service.FinvizScrapper;
import com.codekopf.hotstonks.service.MenuScraper;

import static com.codekopf.hotstonks.model.CrawlingSource.FINVIZ;

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

        List<StockTitle> ownedStocks = new ArrayList<>();
        //
        // Stocks from Degiro
        //
        ownedStocks.add(StockTitle.of(FINVIZ, "AMD", "Advanced Micro Devices, Inc."));
        // Airbus
        ownedStocks.add(StockTitle.of(FINVIZ, "ALK", "Alaska Air Group, Inc."));
        ownedStocks.add(StockTitle.of(FINVIZ, "AAL", "American Airlines Group Inc."));
        ownedStocks.add(StockTitle.of(FINVIZ, "AAPL", "Apple Inc."));
        ownedStocks.add(StockTitle.of(FINVIZ, "T", "AT&T Inc."));
        ownedStocks.add(StockTitle.of(FINVIZ, "BP", "BP p.l.c."));
        ownedStocks.add(StockTitle.of(FINVIZ, "BA", "Boeing"));
        // CEZ a.s.
        // Deutsche Lufthansa AG
        // Erste Group Bank AG - https://www.google.com/finance/quote/EBS:VIE?sa=X&window=5Y ; https://www.boersen-zeitung.de/unternehmen/kurse/AT0000652011
        ownedStocks.add(StockTitle.of(FINVIZ, "F", "Ford Motor"));
        ownedStocks.add(StockTitle.of(FINVIZ, "FNKO", "Funko Inc"));
        ownedStocks.add(StockTitle.of(FINVIZ, "GM", "General Motors Co"));
        ownedStocks.add(StockTitle.of(FINVIZ, "ING", "ING Groep NV"));
        // Kofola CeskoSlovensko as
        ownedStocks.add(StockTitle.of(FINVIZ, "KHC", "Kraft Heinz Co"));
        ownedStocks.add(StockTitle.of(FINVIZ, "M", "Macy's Inc"));
        // Moneta Money Bank as
        ownedStocks.add(StockTitle.of(FINVIZ, "NCLH", "Norwegian Cruise Line Holdings Ltd"));
        ownedStocks.add(StockTitle.of(FINVIZ, "PLUG", "Plug Power Inc"));
        ownedStocks.add(StockTitle.of(FINVIZ, "SHEL", "Shell plc"));
        // RWE AG
        // Schibsted ASA
        ownedStocks.add(StockTitle.of(FINVIZ, "SIX", "Six Flags Entertainment Corp"));
        ownedStocks.add(StockTitle.of(FINVIZ, "LUV", "Southwest Airlines"));
        // Stock Spirits Group PLC
        // Thyssenkrupp AG
        ownedStocks.add(StockTitle.of(FINVIZ, "UL", "Unilever PLC"));
        // Volkswagen AG
        ownedStocks.add(StockTitle.of(FINVIZ, "WFC", "Wells Fargo"));
        ownedStocks.add(StockTitle.of(FINVIZ, "X", "United States Steel"));
        //
        // Stocks from Revolut
        //
        ownedStocks.add(StockTitle.of(FINVIZ, "CSCO", "Cisco"));
        ownedStocks.add(StockTitle.of(FINVIZ, "KO", "The Coca-Cola Company"));
        ownedStocks.add(StockTitle.of(FINVIZ, "MSFT", "Microsoft Corporation"));
        ownedStocks.add(StockTitle.of(FINVIZ, "AMZN", "Amazon.com, Inc."));
        ownedStocks.add(StockTitle.of(FINVIZ, "GOOGL", "Alphabet Inc."));
        ownedStocks.add(StockTitle.of(FINVIZ, "MCD", "McDonald's Corporation"));
        //
        // Stocks from Trading212
        //
        ownedStocks.add(StockTitle.of(FINVIZ, "CNI", "Canadian National Railway"));
        ownedStocks.add(StockTitle.of(FINVIZ, "CP", "Canadian Pacific Railway Limited"));
        ownedStocks.add(StockTitle.of(FINVIZ, "CSX", "CSX Corporation"));
        ownedStocks.add(StockTitle.of(FINVIZ, "NSC", "Norfolk Southern Corporation"));
        // Orsted
        ownedStocks.add(StockTitle.of(FINVIZ, "UNP", "Union Pacific Corporation"));


        List<StockTitle> speculativeStocks = new ArrayList<>();
        speculativeStocks.add(StockTitle.of(FINVIZ, "ARKK", "ARK Innovation ETF"));
        speculativeStocks.add(StockTitle.of(FINVIZ, "ABNB", "AirBnB"));
        speculativeStocks.add(StockTitle.of(FINVIZ, "B4B", "Metro AG"));
        speculativeStocks.add(StockTitle.of(FINVIZ, "GOLD", "Barrick Gold Corp"));
        speculativeStocks.add(StockTitle.of(FINVIZ, "CRM", "Salesforce"));
        speculativeStocks.add(StockTitle.of(FINVIZ, "KR", "Kroger Co."));
        speculativeStocks.add(StockTitle.of(FINVIZ, "NFLX", "Netflix Inc."));
        // Nestlé S.A. - Swiss company, not at FINVIZ
        speculativeStocks.add(StockTitle.of(FINVIZ, "TWLO", "Twilio Inc."));
        // Siemens Gamesa Renewable Energy SA - not at FINVIZ
        speculativeStocks.add(StockTitle.of(FINVIZ, "DOCN", "DigitalOcean Holdings Inc"));
        speculativeStocks.add(StockTitle.of(FINVIZ, "SONY", "Sony Group Corporation"));
        speculativeStocks.add(StockTitle.of(FINVIZ, "LKNCY", "Luckin coffee"));
        speculativeStocks.add(StockTitle.of(FINVIZ, "SBUX", "Starbucks Corporation"));
        speculativeStocks.add(StockTitle.of(FINVIZ, "TSN", "Tyson Foods, Inc."));
        speculativeStocks.add(StockTitle.of(FINVIZ, "TSLA", "Tesla"));

        List<MenuScraper> menuScrapers = new ArrayList<>();

        ownedStocks.forEach(ownedStock -> menuScrapers.add(new FinvizScrapper(ownedStock.getTicker(), date, ownedStock.getTicker())));
        speculativeStocks.forEach(speculativeStock -> menuScrapers.add(new FinvizScrapper(speculativeStock.getTicker(), date, speculativeStock.getTicker())));

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
