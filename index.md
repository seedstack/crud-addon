---
title: "Overview"
addon: "CRUD"
repo: "https://github.com/seedstack/crud-addon"
author: Xián BOULLOSA
description: "Business framework extension to automatically expose CRUD APIs of any DTO."
zones:
    - Addons
tags:
    - api
    - rest
    - business
menu:
    CRUD:
        parent: "contents"
        weight: 10
---

The CRUD add-on is a [business framework]({{< ref "docs/business/index.md" >}}) extension that can expose CRUD operations 
for any Aggregate/DTO couple.<!--more--> 

## REST

One of the most useful way of doing CRUD interaction is through a REST API. This is provided by the `crud-rest` module, 
in the following dependency:

{{< dependency g="org.seedstack.addons.crud" a="crud-rest" >}}

{{% callout ref %}}
For details, see the documentation of the [CRUD REST module]({{< ref "addons/crud/rest.md" >}}).
{{% /callout %}} 

