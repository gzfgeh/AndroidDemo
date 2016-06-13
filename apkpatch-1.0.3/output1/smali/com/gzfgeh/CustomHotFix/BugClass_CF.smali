.class public Lcom/gzfgeh/CustomHotFix/BugClass_CF;
.super Ljava/lang/Object;
.source "BugClass.java"


# direct methods
.method public constructor <init>()V
    .locals 0

    .prologue
    .line 7
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public bug()Ljava/lang/String;
    .locals 1
    .annotation runtime Lcom/alipay/euler/andfix/annotation/MethodReplace;
        method = "bug"
        clazz = "com.gzfgeh.CustomHotFix.BugClass"
    .end annotation

    .prologue
    .line 9
    const-string v0, "fixed class"

    return-object v0
.end method
