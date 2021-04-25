package info.nemoworks.udo.query.builder;

import java.util.ArrayList;
import java.util.List;

import graphql.language.InputValueDefinition;
import graphql.language.TypeName;
import info.nemoworks.udo.graphql.schema.GraphQLPropertyConstructor;

public class FilterDocListInputDefBuilder implements InputValueDefinitionBuilder{
    @Override
    public List<InputValueDefinition> inputValueDefinitionListBuilder(GraphQLPropertyConstructor graphQLPropertyConstructor) {
        List<InputValueDefinition> inputValueDefinitions = new ArrayList<>();
        inputValueDefinitions.add(new InputValueDefinition("filter",new TypeName(graphQLPropertyConstructor.filterKeyWordInQueryXxlist())));
        return inputValueDefinitions;
    }
}