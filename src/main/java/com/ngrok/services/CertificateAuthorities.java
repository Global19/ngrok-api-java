package com.ngrok.services;

import com.ngrok.NgrokApiClient;
import com.ngrok.definitions.*;

import java.util.AbstractMap;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Certificate Authorities are x509 certificates that are used to sign other
 *  x509 certificates. Attach a Certificate Authority to the Mutual TLS module
 *  to verify that the TLS certificate presented by a client has been signed by
 *  this CA. Certificate Authorities  are used only for mTLS validation only and
 *  thus a private key is not included in the resource.
 */
public class CertificateAuthorities {
    private final NgrokApiClient apiClient;

    /**
     * Creates a new sub-client for CertificateAuthorities.
     *
     * @param apiClient an instance of {@link com.ngrok.NgrokApiClient}
     */
    public CertificateAuthorities(final NgrokApiClient apiClient) {
        this.apiClient = Objects.requireNonNull(apiClient, "apiClient is required");
    }
    
    /**
     * A builder object encapsulating state for an unsent Create API call.
     */
    public class CertificateAuthoritiesCreateCallBuilder {
        private String description = "";
        private String metadata = "";
        private final String caPem;

        private CertificateAuthoritiesCreateCallBuilder(
            final String caPem
        ) {
            this.caPem = Objects.requireNonNull(caPem, "caPem is required");
        }
        
        /**
         * human-readable description of this Certificate Authority. optional, max 255
         * bytes.
         *
         * @param description the value of the description parameter as a {@link String}
         * @return the call builder instance
         */
        public CertificateAuthoritiesCreateCallBuilder description(final String description) {
            this.description = Objects.requireNonNull(description, "description is required");
            return this;
        }

        /**
         * human-readable description of this Certificate Authority. optional, max 255
         * bytes.
         *
         * @param description the value of the description parameter as an {@link Optional} of {@link String}
         * @return the call builder instance
         */
        public CertificateAuthoritiesCreateCallBuilder description(final Optional<String> description) {
            this.description = Objects.requireNonNull(description, "description is required").orElse("");
            return this;
        }
        
        /**
         * arbitrary user-defined machine-readable data of this Certificate Authority.
         * optional, max 4096 bytes.
         *
         * @param metadata the value of the metadata parameter as a {@link String}
         * @return the call builder instance
         */
        public CertificateAuthoritiesCreateCallBuilder metadata(final String metadata) {
            this.metadata = Objects.requireNonNull(metadata, "metadata is required");
            return this;
        }

        /**
         * arbitrary user-defined machine-readable data of this Certificate Authority.
         * optional, max 4096 bytes.
         *
         * @param metadata the value of the metadata parameter as an {@link Optional} of {@link String}
         * @return the call builder instance
         */
        public CertificateAuthoritiesCreateCallBuilder metadata(final Optional<String> metadata) {
            this.metadata = Objects.requireNonNull(metadata, "metadata is required").orElse("");
            return this;
        }
        
        /**
         * Initiates the API call asynchronously.
         *
         * @return a {@link CompletionStage} of {@link CertificateAuthority}
         */
        public CompletionStage<CertificateAuthority> call() {
            return apiClient.sendRequest(
                NgrokApiClient.HttpMethod.POST,
                "/certificate_authorities",
                Stream.empty(),
                Stream.of(
                    new AbstractMap.SimpleEntry<>("description", Optional.of(this.description)),
                    new AbstractMap.SimpleEntry<>("metadata", Optional.of(this.metadata)),
                    new AbstractMap.SimpleEntry<>("ca_pem", Optional.of(this.caPem))
                ),
                Optional.of(CertificateAuthority.class)
            );
        }

        /**
         * Initiates the API call and blocks until it returns.
         *
         * @return {@link CertificateAuthority}
         * @throws InterruptedException if the thread was interrupted during the call
         */
        public CertificateAuthority blockingCall() throws InterruptedException {
            try {
                return call().toCompletableFuture().get();
            } catch (final ExecutionException e) {
                throw e.getCause() instanceof RuntimeException ? (RuntimeException) e.getCause() : new RuntimeException(e.getCause().getMessage(), e.getCause());
            }
        }
    }

    /**
     * Upload a new Certificate Authority
     *
     * @param caPem raw PEM of the Certificate Authority
     * @return a call builder for this API call
     */
    public CertificateAuthoritiesCreateCallBuilder create(
        final String caPem
    ) {
        return new CertificateAuthoritiesCreateCallBuilder(
            caPem
        );
    }
    
    /**
     * A builder object encapsulating state for an unsent Delete API call.
     */
    public class CertificateAuthoritiesDeleteCallBuilder {
        private final String id;

        private CertificateAuthoritiesDeleteCallBuilder(
            final String id
        ) {
            this.id = Objects.requireNonNull(id, "id is required");
        }
        
        /**
         * Initiates the API call asynchronously.
         *
         * @return a {@link CompletionStage} of {@link Void}
         */
        public CompletionStage<Void> call() {
            return apiClient.sendRequest(
                NgrokApiClient.HttpMethod.DELETE,
                "/certificate_authorities/" + this.id,
                Stream.empty(),
                Stream.empty(),
                Optional.empty()
            );
        }

        /**
         * Initiates the API call and blocks until it returns.
         *
         * @throws InterruptedException if the thread was interrupted during the call
         */
        public void blockingCall() throws InterruptedException {
            try {
                call().toCompletableFuture().get();
            } catch (final ExecutionException e) {
                throw e.getCause() instanceof RuntimeException ? (RuntimeException) e.getCause() : new RuntimeException(e.getCause().getMessage(), e.getCause());
            }
        }
    }

    /**
     * Delete a Certificate Authority
     *
     * @param id a resource identifier
     * @return a call builder for this API call
     */
    public CertificateAuthoritiesDeleteCallBuilder delete(
        final String id
    ) {
        return new CertificateAuthoritiesDeleteCallBuilder(
            id
        );
    }
    
    /**
     * A builder object encapsulating state for an unsent Get API call.
     */
    public class CertificateAuthoritiesGetCallBuilder {
        private final String id;

        private CertificateAuthoritiesGetCallBuilder(
            final String id
        ) {
            this.id = Objects.requireNonNull(id, "id is required");
        }
        
        /**
         * Initiates the API call asynchronously.
         *
         * @return a {@link CompletionStage} of {@link CertificateAuthority}
         */
        public CompletionStage<CertificateAuthority> call() {
            return apiClient.sendRequest(
                NgrokApiClient.HttpMethod.GET,
                "/certificate_authorities/" + this.id,
                Stream.empty(),
                Stream.empty(),
                Optional.of(CertificateAuthority.class)
            );
        }

        /**
         * Initiates the API call and blocks until it returns.
         *
         * @return {@link CertificateAuthority}
         * @throws InterruptedException if the thread was interrupted during the call
         */
        public CertificateAuthority blockingCall() throws InterruptedException {
            try {
                return call().toCompletableFuture().get();
            } catch (final ExecutionException e) {
                throw e.getCause() instanceof RuntimeException ? (RuntimeException) e.getCause() : new RuntimeException(e.getCause().getMessage(), e.getCause());
            }
        }
    }

    /**
     * Get detailed information about a certficate authority
     *
     * @param id a resource identifier
     * @return a call builder for this API call
     */
    public CertificateAuthoritiesGetCallBuilder get(
        final String id
    ) {
        return new CertificateAuthoritiesGetCallBuilder(
            id
        );
    }
    
    /**
     * A builder object encapsulating state for an unsent List API call.
     */
    public class CertificateAuthoritiesListCallBuilder {
        private Optional<String> beforeId = Optional.empty();
        private Optional<String> limit = Optional.empty();

        private CertificateAuthoritiesListCallBuilder(
        ) {
        }
        
        /**
         * Sets the <code>before_id</code> parameter.
         *
         * @param beforeId the value of the before_id parameter as a {@link String}
         * @return the call builder instance
         */
        public CertificateAuthoritiesListCallBuilder beforeId(final String beforeId) {
            this.beforeId = Optional.ofNullable(beforeId);
            return this;
        }

        /**
         * Sets (or unsets) the <code>before_id</code> parameter.
         *
         * @param beforeId the value of the before_id parameter as an {@link Optional} of {@link String}
         * @return the call builder instance
         */
        public CertificateAuthoritiesListCallBuilder beforeId(final Optional<String> beforeId) {
            this.beforeId = Objects.requireNonNull(beforeId, "beforeId is required");
            return this;
        }
        
        /**
         * Sets the <code>limit</code> parameter.
         *
         * @param limit the value of the limit parameter as a {@link String}
         * @return the call builder instance
         */
        public CertificateAuthoritiesListCallBuilder limit(final String limit) {
            this.limit = Optional.ofNullable(limit);
            return this;
        }

        /**
         * Sets (or unsets) the <code>limit</code> parameter.
         *
         * @param limit the value of the limit parameter as an {@link Optional} of {@link String}
         * @return the call builder instance
         */
        public CertificateAuthoritiesListCallBuilder limit(final Optional<String> limit) {
            this.limit = Objects.requireNonNull(limit, "limit is required");
            return this;
        }
        
        /**
         * Initiates the API call asynchronously.
         *
         * @return a {@link CompletionStage} of a {@link Page} of {@link CertificateAuthorityList}
         */
        public CompletionStage<Page<CertificateAuthorityList>> call() {
            return apiClient.sendRequest(
                NgrokApiClient.HttpMethod.GET,
                "/certificate_authorities",
                Stream.of(
                    new AbstractMap.SimpleEntry<>("before_id", this.beforeId.map(Function.identity())),
                    new AbstractMap.SimpleEntry<>("limit", this.limit.map(Function.identity()))
                ),
                Stream.empty(),
                Optional.of(CertificateAuthorityList.class)
            ).thenApply(list -> new Page<>(apiClient, list));
        }

        /**
         * Initiates the API call and blocks until it returns.
         *
         * @return a {@link Page} of {@link CertificateAuthorityList}
         * @throws InterruptedException if the thread was interrupted during the call
         */
        public Page<CertificateAuthorityList> blockingCall() throws InterruptedException {
            try {
                return call().toCompletableFuture().get();
            } catch (final ExecutionException e) {
                throw e.getCause() instanceof RuntimeException ? (RuntimeException) e.getCause() : new RuntimeException(e.getCause().getMessage(), e.getCause());
            }
        }
    }

    /**
     * List all Certificate Authority on this account
     *
     * @return a call builder for this API call
     */
    public CertificateAuthoritiesListCallBuilder list(
    ) {
        return new CertificateAuthoritiesListCallBuilder(
        );
    }
    
    /**
     * A builder object encapsulating state for an unsent Update API call.
     */
    public class CertificateAuthoritiesUpdateCallBuilder {
        private final String id;
        private Optional<String> description = Optional.empty();
        private Optional<String> metadata = Optional.empty();

        private CertificateAuthoritiesUpdateCallBuilder(
            final String id
        ) {
            this.id = Objects.requireNonNull(id, "id is required");
        }
        
        /**
         * human-readable description of this Certificate Authority. optional, max 255
         * bytes.
         *
         * @param description the value of the description parameter as a {@link String}
         * @return the call builder instance
         */
        public CertificateAuthoritiesUpdateCallBuilder description(final String description) {
            this.description = Optional.ofNullable(description);
            return this;
        }

        /**
         * human-readable description of this Certificate Authority. optional, max 255
         * bytes.
         *
         * @param description the value of the description parameter as an {@link Optional} of {@link String}
         * @return the call builder instance
         */
        public CertificateAuthoritiesUpdateCallBuilder description(final Optional<String> description) {
            this.description = Objects.requireNonNull(description, "description is required");
            return this;
        }
        
        /**
         * arbitrary user-defined machine-readable data of this Certificate Authority.
         * optional, max 4096 bytes.
         *
         * @param metadata the value of the metadata parameter as a {@link String}
         * @return the call builder instance
         */
        public CertificateAuthoritiesUpdateCallBuilder metadata(final String metadata) {
            this.metadata = Optional.ofNullable(metadata);
            return this;
        }

        /**
         * arbitrary user-defined machine-readable data of this Certificate Authority.
         * optional, max 4096 bytes.
         *
         * @param metadata the value of the metadata parameter as an {@link Optional} of {@link String}
         * @return the call builder instance
         */
        public CertificateAuthoritiesUpdateCallBuilder metadata(final Optional<String> metadata) {
            this.metadata = Objects.requireNonNull(metadata, "metadata is required");
            return this;
        }
        
        /**
         * Initiates the API call asynchronously.
         *
         * @return a {@link CompletionStage} of {@link CertificateAuthority}
         */
        public CompletionStage<CertificateAuthority> call() {
            return apiClient.sendRequest(
                NgrokApiClient.HttpMethod.PATCH,
                "/certificate_authorities/" + this.id,
                Stream.empty(),
                Stream.of(
                    new AbstractMap.SimpleEntry<>("description", this.description.map(Function.identity())),
                    new AbstractMap.SimpleEntry<>("metadata", this.metadata.map(Function.identity()))
                ),
                Optional.of(CertificateAuthority.class)
            );
        }

        /**
         * Initiates the API call and blocks until it returns.
         *
         * @return {@link CertificateAuthority}
         * @throws InterruptedException if the thread was interrupted during the call
         */
        public CertificateAuthority blockingCall() throws InterruptedException {
            try {
                return call().toCompletableFuture().get();
            } catch (final ExecutionException e) {
                throw e.getCause() instanceof RuntimeException ? (RuntimeException) e.getCause() : new RuntimeException(e.getCause().getMessage(), e.getCause());
            }
        }
    }

    /**
     * Update attributes of a Certificate Authority by ID
     *
     * @param id the value of the <code>id</code> parameter as a {@link String}
     * @return a call builder for this API call
     */
    public CertificateAuthoritiesUpdateCallBuilder update(
        final String id
    ) {
        return new CertificateAuthoritiesUpdateCallBuilder(
            id
        );
    }
}