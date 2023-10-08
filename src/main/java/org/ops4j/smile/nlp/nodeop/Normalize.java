package org.ops4j.smile.nlp.nodeop;

import org.ops4j.base.BaseNodeOp;
import org.ops4j.cli.NodeOpCLI;
import org.ops4j.exception.OpsException;
import org.ops4j.inf.NodeOp;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.NullNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.google.auto.service.AutoService;

import picocli.CommandLine.Command;
import smile.nlp.normalizer.SimpleNormalizer;

@AutoService(NodeOp.class) @Command(name = "norm",
    mixinStandardHelpOptions = false, description = "Normalize text.")
public class Normalize extends BaseNodeOp<Normalize>
{
  SimpleNormalizer normalizer = SimpleNormalizer.getInstance();

  public Normalize()
  {
    super("norm");
  }

  public JsonNode execute(JsonNode input) throws OpsException
  {
    if (input == null)
    {
      return NullNode.getInstance();
    }
    JsonNode target = getTarget(input);
    if (target == null)
    {
      return NullNode.getInstance();
    }
    return new TextNode(normalizer.normalize(target.asText()));
  }

  public static void main(String args[]) throws OpsException
  {
    NodeOpCLI.cli(new Normalize(), args);
  }
}
