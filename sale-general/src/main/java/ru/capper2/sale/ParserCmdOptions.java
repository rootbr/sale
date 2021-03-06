package ru.capper2.sale;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParserCmdOptions {
  private static final Logger LOGGER = LoggerFactory.getLogger(ParserCmdOptions.class);

  private ParserCmdOptions() {
  }

  public static Map<String, String> parseCmd(String[] args, List<Option> optionsList) {
    Options options = new Options();

    if (optionsList != null && !optionsList.isEmpty()) {
      optionsList.forEach(option -> {
        option.setRequired(true);
        options.addOption(option);
      });

      CommandLine cmd = null;
      try {
        cmd = new DefaultParser().parse(options, args);
      } catch (ParseException e) {
        LOGGER.error(e.getMessage());
        new HelpFormatter().printHelp("sale-utility", options);
        System.exit(1);
      }

      Map<String, String> optionsMap = new HashMap<>();

      if (cmd != null) {
        for (Iterator<Option> it = cmd.iterator(); it.hasNext(); ) {
          Option o = it.next();
          optionsMap.put(o.getLongOpt(), o.getValue());
        }
      }

      return optionsMap;
    } else {
      return null;
    }
  }
}
