<script lang="ts">
	import { createEventDispatcher } from "svelte";
    import RangeSlider from "svelte-range-slider-pips";
    export let device_type:string;
    export let value:number[];
    export let max:number;
    export let min:number;
    export let step: number;
    const dispatch = createEventDispatcher();
    function sendIntensity() {
        dispatch("stop", {value});
    }
</script>

<section>
    <RangeSlider bind:values={value} on:stop={sendIntensity} min={min} max={max} step={step} id="basic-slider"/>
    <div class="flex flex-row justify-center">
        {#if device_type == "light"}
            {value}%
        {/if}
    </div>
</section>