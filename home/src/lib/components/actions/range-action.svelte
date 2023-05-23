<script lang="ts">
    import RangeSlider from "svelte-range-slider-pips";
	let theme = "default";
    export let device_type:string;
    export let value:number;
    export let max:number;
    export let min:number;
    export let step: number;
</script>

<section>
    <RangeSlider values = {[value]} on:change={(evt)  => value = evt.detail.value} min={min} max={max} step={step} id="basic-slider"/>
    <div class="flex flex-row justify-center">
        {#if device_type == "light"}
            {value}%
        {/if}
    </div>
</section>