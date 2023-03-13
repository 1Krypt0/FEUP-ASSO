<script lang="ts">

    import { page } from '$app/stores';

	export let data;

    const type = $page.params.page;
    const device = {
        name: "Bedroom Light",
        status: "CONNECTED",
        currentValue: "1"
    }

    function toggleDevice() {
		device.currentValue = device.currentValue == "0" ? "1" : "0";
        console.log("button", device.currentValue);
        on = !on
	}

    let on = false;

    $: on_class = !on ? `${data.colors[type][400]} text-white border-white` : `bg-white text-amber-400`;

</script>

<section class="px-5 py-4 md:px-28 md:py-4">
    <h1 class="text-lights-400 text-4xl md:text-6xl font-bold"> {device.name }</h1>
    <section class="pt-8">

        <button on:click={toggleDevice} class="{on_class} border-2 font-bold rounded-2xl py-4 px-20">
            {#if device.currentValue == "0"}
				Turn On
            {:else}
                Turn Off
            {/if}
        </button>
    </section>
</section>
