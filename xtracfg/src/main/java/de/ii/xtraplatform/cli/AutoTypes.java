package de.ii.xtraplatform.cli;

import de.ii.ldproxy.cfg.LdproxyCfg;
import de.ii.xtraplatform.entities.domain.EntityFactory;
import de.ii.xtraplatform.features.domain.FeatureProviderDataV2;

public class AutoTypes {

  static EntityFactory getFactory(
      LdproxyCfg ldproxyCfg,
      EntityType entityType,
      ProviderType providerType,
      FeatureProviderType featureProviderType) {
    return ldproxyCfg
        .getEntityFactories()
        .get(
            entityType.toString(),
            String.format("%s/%s", providerType.toString(), featureProviderType.toString()));
  }

  static FeatureProviderDataV2.Builder<?> getBuilder(
      LdproxyCfg ldproxyCfg,
      EntityType entityType,
      ProviderType providerType,
      FeatureProviderType featureProviderType) {
    return ((FeatureProviderDataV2.Builder<?>)
            getFactory(ldproxyCfg, entityType, providerType, featureProviderType)
                .emptyDataBuilder())
        .providerType(providerType.toString())
        .providerSubType(featureProviderType.toString());
  }

  enum Type {
    ENTITIES,
    UNKNOWN;

    @Override
    public String toString() {
      return name().toLowerCase();
    }
  }

  enum EntityType {
    PROVIDERS,
    SERVICES,
    UNKNOWN;

    @Override
    public String toString() {
      return name().toLowerCase();
    }
  }

  enum ProviderType {
    FEATURE,
    TILE,
    UNKNOWN;

    @Override
    public String toString() {
      return name().toLowerCase();
    }
  }

  enum FeatureProviderType {
    PGIS("sql"),
    GPKG("sql"),
    WFS("wfs"),
    UNKNOWN("unknown");

    private final String subType;

    FeatureProviderType(String subType) {
      this.subType = subType;
    }

    @Override
    public String toString() {
      return subType;
    }
  }
}
