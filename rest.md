---
title: "REST"
addon: "CRUD"
repo: "https://github.com/seedstack/crud-addon"
zones:
    - Addons
weight: -1    
tags:
    - api
    - rest
    - business
menu:
    CRUD:
        parent: "contents"
        weight: 20
---

This module can expose REST APIs for doing CRUD operations on aggregates, through their DTO.<!--more--> 

## Dependency

Enabling automatic REST publication of CRUD operations requires the following dependency in your project:

{{< dependency g="org.seedstack.addons.crud" a="crud-rest" >}}

## Usage

To be exposed as a CRUD REST API, the DTO:

* Must be annotated with {{< java "org.seedstack.business.assembler.DtoOf" "@" >}}, linking it to its corresponding aggregate. 
* Must have a [default]({{< ref "docs/business/assemblers.md#default-assembler" >}}) or a 
[custom]({{< ref "docs/business/assemblers.md#custom-assembler" >}}) assembler that can handle the mapping to its 
aggregate and back. 
* Must be properly annotated so the [fluent assembler DSL]({{< ref "docs/business/fluent-assembler.md" >}}) can the mapping automatically.  

As an example consider the class `CustomerDto` below, corresponding to the `Customer` aggregate and annotated so the fluent
assembler can do the mapping automatically:

```java
@DtoOf(Customer.class)
public class CustomerDto {
    private String id;
    
    @AggregateId
    @FactoryArgument
    private String getId() {
        return id;
    }
    
    private void setId(String id) {
        this.id = id;
    }
}
```  

{{% callout ref %}}
If you're not familiar with business framework assemblers and its fluent assembler DSL, read 
[this documentation]({{< ref "docs/business/assemblers.md" >}}). 
{{% /callout %}}

### Default resource

To generate a default REST resource exposing CRUD operations for a DTO, annotate the DTO with {{< java "org.seedstack.crud.rest.RestCrud" "@" >}}:

```java
@DtoOf(Customer.class)
@RestCrud("/customers")
public class CustomerDto {
    private String id;
    
    @AggregateId
    @FactoryArgument
    private String getId() {
        return id;
    }
    
    private void setId(String id) {
        this.id = id;
    }
}
```  

{{% callout info %}}
You can control which CRUD operation(s) are exposed by using the corresponding {{< java "org.seedstack.crud.rest.RestCrud" "@" >}}
boolean parameters.
{{% /callout %}}

### Custom resource

Coming soon... 
