#!/bin/bash

(
cat << EOF

scrape_configs:
  - job_name:       'rest-example'

    # Override the global default and scrape targets from this job every 5 seconds.
    scrape_interval: 5s

    static_configs:
      - targets: ['localhost:8080']
        labels:
          group: 'production'

EOF
) > /usr/src/prometheus/prometheus.yml